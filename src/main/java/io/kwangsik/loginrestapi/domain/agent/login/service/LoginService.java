package io.kwangsik.loginrestapi.domain.agent.login.service;

import io.kwangsik.loginrestapi.domain.agent.login.dto.LoginOne;
import io.kwangsik.loginrestapi.domain.user.service.UserService;
import io.kwangsik.loginrestapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Value("${kwangsik.login.jwtSecret:KwangsikLoginJwtSecret}")
    private String loginJwtSecret;
    @Value("${kwangsik.login.jwtExpirationMs:86400000}") /* 24시간 = 24 * 60 * 60 * 1000 */
    private int loginJwtExpirationMs;

    public LoginOne loginForAgent(String loginId, String password, HttpSession httpSession) {
        final LoginOne one = authenticate(loginId, password);

        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        return one.updateToken(httpSession.getId());
    }

    public LoginOne getToken(String loginId, String password) {
        final LoginOne one = authenticate(loginId, password);

        return one.updateToken(JwtUtil.generate(one.getLoginId(), this.loginJwtExpirationMs, this.loginJwtSecret));
    }

    // private ----------
    private LoginOne authenticate(String loginId, String password) {
        final UsernamePasswordAuthenticationToken authenticationToken;
        final Authentication authentication;

        log.debug("$$ LoginApiController.authenticate() - loginId: " + loginId);
        authenticationToken = new UsernamePasswordAuthenticationToken(loginId, password);
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            log.warn("$$ LoginApiController.authenticate() - BadCredentialsException: " + loginId);
            throw new RuntimeException("사용자ID 또는 비밀번호가 잘못되었습니다. 다시 입력하십시오.");
        } catch (Exception e) {
            log.error("$$ LoginApiController.authenticate() - Exception: " + loginId);
            e.printStackTrace();
            throw new RuntimeException("로그인 요청시 오류가 발생했습니다.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginOne(((UserDetails) authentication.getPrincipal()).getUsername(),
                this.userService.findVOBy(loginId));
    }
}