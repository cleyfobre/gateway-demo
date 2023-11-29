# gateway-demo

This is super simple gateway demo using Spring Cloud Gateway.

#### Configure in application.yml

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: myservice
          uri: ${MYSERVICE_ROUTE_URI:http://localhost:6204}
          predicates:
            - Path=/test
          filters:
            - PrefixPath=/api
            - AddResponseHeader=X-Powered-By,Young Gateway
```

#### Configure with Java fluent API

```java
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
			.build();
	}

}
```
