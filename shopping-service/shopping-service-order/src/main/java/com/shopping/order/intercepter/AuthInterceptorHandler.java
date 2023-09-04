package com.shopping.order.intercepter;

import api.R;
import api.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.order.config.properties.JwtProperties;
import com.shopping.order.util.JwtUtil;
import error.RRException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptorHandler implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public final static String GLOBAL_JWT_USER_INFO="jwttoken:usermember:info";

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入前置拦截器");


        /* ----------------------------------------------------------------------spring session 鉴权模式
        if(!ObjectUtils.isEmpty(request.getSession().getAttribute("member"))) {
            return true;
        }
        -------------------------------------------------------------------------*/

        String message = null;
        String authorization = request.getHeader(jwtProperties.getTokenHeader());
        log.info("authorization:"+authorization);
        //校验token

        if(!StringUtils.isEmpty(authorization)
                && authorization.startsWith(jwtProperties.getTokenHead())){
            String authToken = authorization.substring(jwtProperties.getTokenHead().length());
            //解析jwt-token
            Claims claims = null;
            try {
                claims = jwtUtil.parseJWT(authToken);
                if(claims != null){
                    request.setAttribute(GLOBAL_JWT_USER_INFO,claims);
                    return true;
                }
            } catch (RRException e) {
                log.error(message = (e.getMessage()+":"+authToken));
            }
        }

        print(response, "您没有访问权限！请先登录。");
        return false;
    }

    protected void print(HttpServletResponse response,String message) throws Exception{
        /**
         * 设置响应头
         */
        response.setHeader("Content-Type","application/json");
        response.setCharacterEncoding("UTF-8");
        String result = new ObjectMapper().writeValueAsString(R.data(ResultCode.UN_AUTHORIZED, message));
        response.getWriter().print(result);

    }
}
