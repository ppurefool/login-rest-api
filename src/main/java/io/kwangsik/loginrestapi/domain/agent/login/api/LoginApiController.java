package io.kwangsik.loginrestapi.domain.agent.login.api;

import io.kwangsik.loginrestapi.common.ApiOne;
import io.kwangsik.loginrestapi.common.BaseApiController;
import io.kwangsik.loginrestapi.domain.agent.login.dto.LoginCommand;
import io.kwangsik.loginrestapi.domain.agent.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginApiController extends BaseApiController {
    private final LoginService service;

    @GetMapping("/api/agent/login/{loginId}")
    public ResponseEntity<ApiOne> loginForTest(@PathVariable("loginId") String loginId, HttpSession httpSession) {
        log.debug("$$ LoginApiController.loginForTest() - loginId: " + loginId);
        return login(loginId, "$2a$10$IpQpdvYynWy4.X7XiC2F7.jvmv.EPX5mQ6qDeu6YrVsuhNhaDuBjK", httpSession);
    }

    @PostMapping("/api/agent/login")
    public ResponseEntity<ApiOne> loginForAgent(@ModelAttribute LoginCommand command, HttpSession httpSession) {
        log.debug("$$ LoginApiController.loginForAgent() - LoginCommand: " + command);
        return login(command.getLoginId(), command.getPassword(), httpSession);
    }

    private ResponseEntity<ApiOne> login(String loginId, String password, HttpSession httpSession) {
        try {
            return ok("로그인이 완료되었습니다.", this.service.login(loginId, password, httpSession));
        } catch (RuntimeException e) {
            return badRequest(e.getMessage());
        }
    }
}