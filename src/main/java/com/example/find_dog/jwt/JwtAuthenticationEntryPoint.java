package com.example.find_dog.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 유효한 자격 증명을 제공하지 않고 접근하려 할 때 401 Unauthorized 에러를 send하는 클래스
* */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // 401에러 응답
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}