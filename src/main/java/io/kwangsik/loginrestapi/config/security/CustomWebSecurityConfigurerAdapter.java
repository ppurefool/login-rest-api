package io.kwangsik.loginrestapi.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@RequiredArgsConstructor
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Value("${security.ignore-patterns}")
    private String[] ignorePatterns;
    @Value("${security.permit-all-patterns}")
    private String[] permitAllPatterns;

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
                    authenticated();
    }

    @Bean /* for LoginApiController */
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}