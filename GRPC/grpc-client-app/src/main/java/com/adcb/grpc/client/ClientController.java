package com.adcb.grpc.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    @GetMapping("/call")
    public RpcResponse clientResponse(){

        RpcClient client = new RpcClient("http://localhost:9595/rpc");  // ensure this is correct

        Mono<RpcResponse> respMono = client.invoke("sayHello", Map.of("name", "Alice"))
                .doOnError(e -> {
                    System.err.println("RPC invocation failed:");
                    e.printStackTrace();
                });

        RpcResponse resp;
        try {
            resp = respMono.block();
            if (resp != null) {
                System.out.println("Result = " + resp.getResult() + ", error = " + resp.getError());
            } else {
                System.out.println("Received null response");
            }
            return resp;
        } catch (Exception ex) {
            System.err.println("Error while blocking on response:");
            ex.printStackTrace();
            // Exit with non-zero to indicate failure
            System.exit(1);

        }


        return null;
    }


}

