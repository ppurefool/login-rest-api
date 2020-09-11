package io.kwangsik.loginrestapi.config.security;

import io.kwangsik.loginrestapi.util.CheckUtil;
import io.kwangsik.loginrestapi.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomTokenAuthenticationFilter extends OncePerRequestFilter {
    private String solupiaLoginJwtSecret;
    private UserDetailsService userDetailsService;

    public CustomTokenAuthenticationFilter(String newSolupiaLoginJwtSecret, UserDetailsService newUserDetailsService) {
        this.solupiaLoginJwtSecret = newSolupiaLoginJwtSecret;
        this.userDetailsService = newUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String xAuthToken = request.getHeader("x-auth-token");
        final String username;
        final UserDetails userDetails;
        final UsernamePasswordAuthenticationToken authentication;

        if (CheckUtil.isNotBlank(xAuthToken)) {
            log.debug("$$ AuthTokenFilter.doFilterInternal() - xAuthToken: {}", xAuthToken);
            if (JwtUtil.validate(xAuthToken, this.solupiaLoginJwtSecret)) {
                username = JwtUtil.get(xAuthToken, this.solupiaLoginJwtSecret);
                log.debug("$$ AuthTokenFilter.doFilterInternal() - username: {}", username);
                userDetails = getUserDetails(username);
                if (null != userDetails) {
                    authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    // private ----------
    private UserDetails getUserDetails(String username) {
        try {
            return this.userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            log.warn("$$ AuthTokenFilter.getUserDetails() - UsernameNotFoundException: {0}", e);
        }
        return null;
    }
}