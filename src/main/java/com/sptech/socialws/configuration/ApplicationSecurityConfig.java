package com.sptech.socialws.configuration;

import com.sptech.socialws.configuration.entrypoint.AccessDeniedCustomEntryPoint;
import com.sptech.socialws.usecase.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@Configuration
@SuppressWarnings("unused")
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AuthServiceImpl userService;

    private static final String[] AUTH_WHITELIST = {
        "/authentication/login",
        "/authentication/create",
        "/h2-console/**"
    };

    @Autowired
    public ApplicationSecurityConfig(
            PasswordEncoder passwordEncoder,
            AuthServiceImpl userService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST)
                    .permitAll()
                .antMatchers("/posts/find-all")
                    .hasAnyRole("USER", "ARTIST")
                .antMatchers("/posts/**")
                    .hasRole("ARTIST")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint())
                    .accessDeniedHandler(accessDeniedHandler())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .logout()
                .deleteCookies("remove")
                .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AccessDeniedCustomEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new AccessDeniedCustomEntryPoint();
    }
}
