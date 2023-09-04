package com.shopping.gateway.master.filter;

import com.shopping.gateway.master.utils.MasterGatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import utils.JwtUtil;

@Slf4j
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 1.获取令牌信息
        // 1.1.从参数中获取令牌信息
        String token = request.getQueryParams().getFirst(MasterGatewayConstant.AUTHORIZE_TOKEN);

        // 1.2.从头部获取令牌信息
        if (StringUtils.isBlank(token)) token = request.getHeaders().getFirst(MasterGatewayConstant.AUTHORIZE_TOKEN);

        // 1.3.从cookie获取令牌信息
        if (StringUtils.isBlank(token)) {
            HttpCookie cookie = request.getCookies().getFirst(MasterGatewayConstant.AUTHORIZE_TOKEN);
            if (cookie != null && StringUtils.isNotBlank(cookie.getValue())) token = cookie.getValue();
        }

        // token校验及解密
        if (StringUtils.isBlank(token)) {
            // 为空时，返回错误
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        } else {
            // 非空时，校验token
            try {
                JwtUtil.parseJWT(token);
                return chain.filter(exchange);
            } catch (Exception e) {
                log.error("授权失败！" + e);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
        }

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
