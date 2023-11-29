package com.example.gatewaydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayDemoApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route(r -> r.path("/test")
				.filters(f -> f
					.prefixPath("/api")
					.addResponseHeader("X-Powered-By", "Young Gateway")
				)
				.uri("http://localhost:6204")
			)
			.route(r -> r.path("/health-check")
				.filters(f -> f
					.addResponseHeader("X-Powered-By", "Young Gateway")
				)
				.uri("http://localhost:6204")
			)
			.build();
	}

}
