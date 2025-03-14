package ma.iga.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    //static
    //@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("r1", r -> r.path("/employes/**").uri("http://localhost:8081"))
                .route("r2", r -> r.path("/departements/**").uri("http://localhost:8081"))
                .route("r3", r -> r.path("/soldes/**").uri("http://localhost:8082"))
                .route("r4", r -> r.path("/demandes/**").uri("http://localhost:8082"))
//                .route("r1", r -> r.path("/employes/**").uri("lb://SERVICE-EMPLOYES"))
//                .route("r2", r -> r.path("/departements/**").uri("lb://SERVICE-EMPLOYES"))
                .build();
    }

    //dynamique
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(
            ReactiveDiscoveryClient rdc,
            DiscoveryLocatorProperties properties){
        return new DiscoveryClientRouteDefinitionLocator(rdc, properties);
    }

}
