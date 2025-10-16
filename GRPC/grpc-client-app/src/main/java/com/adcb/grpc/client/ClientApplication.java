package com.adcb.grpc.client;

import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.Map;

@SpringBootApplication
public class ClientApplication  {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }
}
