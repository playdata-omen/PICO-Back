package kr.omen.pico.config;

import kr.omen.pico.config.jwt.JwtAccessDeniedHandler;
import kr.omen.pico.config.jwt.JwtAuthenticationEntryPoint;
import kr.omen.pico.config.jwt.JwtSecurityConfig;
import kr.omen.pico.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity  // 스프링 시큐리티 필터(SecurityConfig 클래스)가 스프링 필터 체인에 등록되게 하는 어노테이션
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)  // Secured 어노테이션을 활성화시키는 어노테이션
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 개발 편의성을 위해 CSRF 프로텍션을 비활성화
                .csrf()
                .disable()
                // HTTP 기본 인증 비활성화
                .httpBasic()
                .disable()
                // 폼 기반 인증 비활성화
                .formLogin()
                .disable()
                // stateless한 세션 정책 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

//        httpSecurity.csrf().disable();
//        httpSecurity.authorizeRequests()
//                .antMatchers("/user/**").authenticated()   // /user로 들어오는 모든 요청은 권한 필요
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")   // /admin으로 들어오는 모든 요청은 ADMIN이라는 역할이 있는 사람만 접근 가능 (스프링 시큐리티에서는 항상 ROLE_ADMIN 이런 형태여야 한다)
//                .anyRequest().permitAll()  // 그외 요청은 모두 접근 가능
//                .and()
//                .logout()
//                .logoutSuccessUrl("/");
//                .defaultSuccessUrl("/login-success")
    }

}