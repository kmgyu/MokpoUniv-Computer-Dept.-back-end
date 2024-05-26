package org.mokpouniv.computerDept_backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CORSConfig corsConfig;

    // PasswordEncoder는 BCryptPasswordEncoder를 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))

                // enable h2-console
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()
                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                    .sessionManagement(sessionManagement -> sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                    .formLogin(AbstractHttpConfigurer::disable)
//                    .httpBasic(AbstractHttpConfigurer::disable);

                // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정하겠다.
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login/authenticate").permitAll() // 로그인 api
                        .requestMatchers("/login/signup").permitAll() // 회원가입 api
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()// h2-console, favicon.ico 요청 인증 무시
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/notice/**").permitAll()
                        .requestMatchers("/notice/create").hasAuthority("ROLE_USER") // 유저 권한 존재 시
                        .requestMatchers("/question/**").permitAll()
                        .requestMatchers("/question/create").permitAll()
                        .requestMatchers("/comment/**").permitAll()
                                .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated() // 그 외 인증 없이 접근X
                )
                .addFilter(corsConfig.corsFilter())
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);// JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig class 적용


        return httpSecurity.build();
    }

}
