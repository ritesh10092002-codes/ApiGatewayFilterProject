package com.example.ApiGateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class ApiGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path=exchange.getRequest().getURI().getPath();
        System.out.println("incoming request: "+path);

        // Continue request
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    System.out.println("✅ Response sent for: " + path);
                })
        );


    }

    @Override
    public int getOrder() {
        return -1; //higher authority
    }
}
