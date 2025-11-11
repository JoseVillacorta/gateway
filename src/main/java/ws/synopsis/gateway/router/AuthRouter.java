package ws.synopsis.gateway.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.Map;

@Configuration
public class AuthRouter {

    @Bean
    public RouterFunction<ServerResponse> authRoutes() {
        return RouterFunctions
                .route()
                .GET("/authorized", this::authorize)
                .build();
    }

    public Mono<ServerResponse> authorize(ServerRequest request) {
        String code = request.queryParam("code").orElse("");
        Map<String, String> response = Collections.singletonMap("authorizationCode", code);
        return ServerResponse.ok().bodyValue(response);
    }
}
