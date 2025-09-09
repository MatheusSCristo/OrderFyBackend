package com.orderfy.gateway_service.filter;

import com.orderfy.gateway_service.config.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RefreshScope // Permite atualizar configurações dinamicamente se usar Spring Cloud Config
public class AuthenticationFilter implements GlobalFilter, Ordered {

    private final JwtUtil jwtUtil;
    private final RouterValidator routerValidator;

    public AuthenticationFilter(JwtUtil jwtUtil, RouterValidator routerValidator) {
        this.jwtUtil = jwtUtil;
        this.routerValidator = routerValidator;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            // 1. Verificar se o cabeçalho de autorização existe
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return this.onError(exchange, HttpStatus.UNAUTHORIZED, "Missing authorization header");
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            // 2. Validar o formato "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return this.onError(exchange, HttpStatus.UNAUTHORIZED, "Invalid authorization format");
            }

            String token = authHeader.substring(7);

            // 3. Validar o token (assinatura e expiração)
            try {
                if (!jwtUtil.validateTokenSignature(token) || jwtUtil.isTokenExpired(token)) {
                    return this.onError(exchange, HttpStatus.UNAUTHORIZED, "Token expired or invalid signature");
                }
            } catch (Exception e) {
                return this.onError(exchange, HttpStatus.INTERNAL_SERVER_ERROR, "Token validation error");
            }

            // 4. Injetar claims nos cabeçalhos para microsserviços downstream (opcional, mas recomendado)
            Claims claims = jwtUtil.extractAllClaims(token);
            exchange.getRequest().mutate()
                    .header("X-User-Role", claims.get("role", String.class))
                    .header("X-Restaurant-Id", claims.get("restaurantId", String.class));
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus status, String errorMessage) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        // Opcional: retornar a mensagem de erro no corpo da resposta
        // response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        // return response.writeWith(Mono.just(response.bufferFactory().wrap(errorMessage.getBytes())));
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1; // Executar este filtro com alta prioridade
    }
}