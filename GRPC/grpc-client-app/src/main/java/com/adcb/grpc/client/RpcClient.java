package com.adcb.grpc.client;

import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;

import java.util.Map;

public class RpcClient {
    private final String baseUrl;

    public RpcClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Mono<RpcResponse> invoke(String method, Map<String, Object> params) {
        RpcRequest req = new RpcRequest(method, params);
        return Mono.fromCallable(() -> RpcCodec.encodeRequest(req))
                .flatMap(bytes ->
                        HttpClient.create()
                                .protocol(HttpProtocol.H2C, HttpProtocol.HTTP11) // or HttpProtocol.H2C if cleartext HTTP/2 is used
                                .post()
                                .uri(baseUrl + "/rpc")
                                .send(ByteBufFlux.fromInbound(Mono.just(bytes)))
                                .responseSingle((resp, buf) ->
                                        buf
                                                .asByteArray()
                                                .flatMap(byteArr -> {
                                                    try {
                                                        RpcResponse respObj = RpcCodec.decodeResponse(byteArr);
                                                        return Mono.just(respObj);
                                                    } catch (Exception e) {
                                                        return Mono.error(e);
                                                    }
                                                })
                                )
                );
    }
}