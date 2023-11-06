package com.logicalj.bms.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/movies-service/**").uri("lb://movies-service"))
                .route(p -> p.path("/showtime-service/**").uri("lb://showtime-service"))
                .route(p -> p.path("/theaters-service/**").uri("lb://theaters-service"))
                //.route(p -> p.path("/").uri())
                .build();
    }
}
