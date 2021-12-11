package kr.omen.pico.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Web Security 설정
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)  // Secured 어노테이션을 활성화시키는 어노테이션
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // 기본값이 on인 csrf 취약점 보안을 해제한다. on으로 설정해도 되나 설정할경우 웹페이지에서 추가처리가 필요함.
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
        http.headers().
                frameOptions().sameOrigin() // SockJS는 기본적으로 HTML iframe 요소를 통한 전송을 허용하지 않도록 설정되는데 해당 내용을 해제한다.
            .and()
                .formLogin() // 권한없이 페이지 접근하면 로그인 페이지로 이동한다.
            .and()
                .authorizeRequests()
                .antMatchers("/templates/**").hasRole("USER") // chat으로 시작하는 리소스에 대한 접근 권한 설정
                .antMatchers("/user/**").authenticated()   // /user로 들어오는 모든 요청은 권한 필요
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")  // /admin으로 들어오는 모든 요청은 ADMIN이라는 역할이 있는 사람만 접근 가능 (스프링 시큐리티에서는 항상 ROLE_ADMIN 이런 형태여야 한다)
                .anyRequest().authenticated().and().formLogin().and().httpBasic();  // 그외 요청은 모두 접근 가능

//        http.authorizeRequests()
//                .antMatchers("/user/**").authenticated()   // /user로 들어오는 모든 요청은 권한 필요
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")   // /admin으로 들어오는 모든 요청은 ADMIN이라는 역할이 있는 사람만 접근 가능 (스프링 시큐리티에서는 항상 ROLE_ADMIN 이런 형태여야 한다)
//                .anyRequest().permitAll()  // 그외 요청은 모두 접근 가능
//                .and()
//                .logout()
//                .logoutSuccessUrl("/");
//                .defaultSuccessUrl("/login-success")
    }

    /**
     * 테스트를 위해 In-Memory에 계정을 임의로 생성한다.
     * 서비스에 사용시에는 DB데이터를 이용하도록 수정이 필요하다.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("KJM123")
                .password("{noop}1234")
                .roles("USER")
                .and()
                .withUser("LGH123")
                .password("{noop}1234")
                .roles("USER")
                .and()
                .withUser("우송123")
                .password("{noop}1234")
                .roles("USER")
                .and()
                .withUser("재훈123")
                .password("{noop}1234")
                .roles("USER")
                .and()
                .withUser("하운123")
                .password("{noop}1234")
                .roles("GUEST");

    }
}
