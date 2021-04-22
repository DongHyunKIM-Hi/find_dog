package com.example.find_dog.config;

import com.example.find_dog.jwt.JwtAccessDeniedHandler;
import com.example.find_dog.jwt.JwtAuthenticationEntryPoint;
import com.example.find_dog.jwt.JwtSecurityConfig;
import com.example.find_dog.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * 추가적인 설정을 위해서 WebSecurityConfigurer를 implements 하거나
 * WebSecurityConfigurerAdapter를 extends하는 방법이 있음
 */
@Configuration
@EnableWebSecurity                                  // 기본적인 Web 보안을 활성화 하겠다는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true)  // @preAuthorize 어노테이션을 메소드 단위로 추가하기 위해서 적용
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // PasswordEncoder는 BCryptPasswordEncoder를 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // 해당 요청들은 Spring Security 로직을 수행하지 않도록 무시하는 것으로 설정
        web.ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/error"
                );
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable
                .csrf().disable()

                // exception을 핸들링할 때 authenticationEntryPoint와 accessDeniedHandler를
                // jwt 패키지내에 만들었던 클래스들로 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 세션 설정을 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                /*
                 * HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
                 * .authenticated() : 해당 요청은 인증되어야 한다는 의미
                 * .permitAll() : 인증없이 접근을 허용하겠다는 의미
                 */
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/article").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/article/{article_id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/article/{article_id}").authenticated()
                .antMatchers(HttpMethod.POST, "/api/comment/{article_id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/comment/{comment_id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/comment/{comment_id}").authenticated()
                .anyRequest().permitAll()


                // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}