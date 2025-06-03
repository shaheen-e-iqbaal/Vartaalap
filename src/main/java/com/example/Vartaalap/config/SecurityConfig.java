package com.example.Vartaalap.config;

import com.example.Vartaalap.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import java.io.IOException;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig extends AbstractHttpSessionApplicationInitializer {

    private final UserService userService;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(@Lazy UserService userService, CustomAccessDeniedHandler accessDeniedHandler) {
        this.userService = userService;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Custom success handler to return HTTP 200 and a message on login success
        AuthenticationSuccessHandler successHandler = (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Login successful\"}");
            response.getWriter().flush();
        };

        // Custom failure handler to return HTTP 401 with message on login failure
        SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request,
                                                HttpServletResponse response,
                                                org.springframework.security.core.AuthenticationException exception) throws IOException {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Invalid username or password\"}");
                response.getWriter().flush();
            }
        };

        // Custom logout success handler to return HTTP 200 with a message
        LogoutSuccessHandler logoutSuccessHandler = (request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Logout successful\"}");
            response.getWriter().flush();
        };

        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity in API testing (optional but recommended for APIs)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/register", "/login").permitAll()
                        .anyRequest().authenticated()
                ).
                exceptionHandling(exception -> exception
                    .accessDeniedHandler(accessDeniedHandler) // 👈 custom handler
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/login")      // POST /login with username & password (form parameters)
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessHandler(logoutSuccessHandler)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Used by Spring Security to handle authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
