package com.adcb.grpc_server.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import java.awt.*;
import java.util.Map;

@RestController
public class RpcController {

    @PostMapping(value = "/rpc",
            consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<byte[]> rpc(@RequestBody Mono<byte[]> bodyMono) {
        return bodyMono.flatMap(bytes -> {
            try {
                RpcRequest req = RpcCodec.decodeRequest(bytes);
                return handle(req).map(resp -> {
                    try {
                        return RpcCodec.encodeResponse(resp);
                    } catch (Exception e) {
                        RpcResponse err = new RpcResponse(null, "Encode error: " + e.getMessage());
                        try {
                            return RpcCodec.encodeResponse(err);
                        } catch (Exception ex2) {
                            return new byte[0];
                        }
                    }
                });
            } catch (Exception ex) {
                RpcResponse err = new RpcResponse(null, "Bad request: " + ex.getMessage());
                try {
                    return Mono.just(RpcCodec.encodeResponse(err));
                } catch (Exception e2) {
                    return Mono.just(new byte[0]);
                }
            }
        });
    }

    private Mono<RpcResponse> handle(RpcRequest req) {
        String method = req.getMethod();
        if ("sayHello".equals(method)) {
            Object nameObj = req.getParams().get("name");
            String name = (nameObj != null) ? nameObj.toString() : "";
            return Mono.just(new RpcResponse(
                    Map.of("greeting", "Hello, " + name),
                    null
            ));
        } else {
            return Mono.just(new RpcResponse(null, "Unknown method: " + method));
        }
    }
}