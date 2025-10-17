package io.github.gustacodes.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class FlechGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlechGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/auth/**").uri("lb://fleche-api"))
                .route(r -> r.path("/like/**").uri("lb://fleche-api"))
                .route(r -> r.path("/location/**").uri("lb://fleche-api"))
                .route(r -> r.path("/profile/**").uri("lb://fleche-api"))
                .route(r -> r.path("/user/**").uri("lb://fleche-api"))
                .route(r -> r.path("/session/**").uri("lb://fleche-api"))
                .build();
    }

}
