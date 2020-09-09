package io.kwangsik.loginrestapi.domain.agent.login.service;

import io.kwangsik.loginrestapi.domain.agent.login.dto.LoginOne;
import io.kwangsik.loginrestapi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public LoginOne login(String loginId, String password, HttpSession httpSession) {
        final UsernamePasswordAuthenticationToken authenticationToken;
        final Authentication authentication;

        log.debug("$$ LoginApiController.login() - loginId: " + loginId);
        authenticationToken = new UsernamePasswordAuthenticationToken(loginId, password);
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            log.warn("$$ LoginApiController.login() - BadCredentialsException: " + loginId);
            e.printStackTrace();
            throw new RuntimeException("사용자ID 또는 비밀번호가 잘못되었습니다. 다시 입력하십시오.");
        } catch (Exception e) {
            log.warn("$$ LoginApiController.login() - Exception: " + loginId);
            e.printStackTrace();
            throw new RuntimeException("로그인 요청시 오류가 발생했습니다.");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
        log.debug("$$ LoginApiController.login() - getId(): " + httpSession.getId());
        return new LoginOne(this.userService.findVOBy(loginId), httpSession.getId());
    }
}