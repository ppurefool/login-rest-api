package io.kwangsik.loginrestapi.config.security;

import io.kwangsik.loginrestapi.config.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Value("${security.ignore-patterns}")
    private String[] ignorePatterns;
    @Value("${security.permit-all-patterns}")
    private String[] permitAllPatterns;
    @Value("${kwangsik.login.jwtSecret:KwangsikLoginJwtSecret}")
    private String loginJwtSecret;

    @Autowired
    public CustomWebSecurityConfigurerAdapter(CustomUserDetailsService customUserDetailsService) {
        this.userDetailsService = customUserDetailsService;
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(ignorePatterns);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
            formLogin().
                loginProcessingUrl("/login-process").
                    permitAll().
                        and().
            authorizeRequests().
                antMatchers(HttpMethod.OPTIONS, "/**").
                    permitAll().
                antMatchers(permitAllPatterns).
                    permitAll().
                anyRequest().
                    authenticated().
                        and().
            csrf().
                disable().
            addFilterBefore(customTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomTokenAuthenticationFilter customTokenAuthenticationFilter() {
        return new CustomTokenAuthenticationFilter(this.loginJwtSecret, this.userDetailsService);
    }
}