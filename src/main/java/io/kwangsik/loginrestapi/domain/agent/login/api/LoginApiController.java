package io.kwangsik.loginrestapi.domain.agent.login.api;

import io.kwangsik.loginrestapi.common.ApiOne;
import io.kwangsik.loginrestapi.common.BaseApiController;
import io.kwangsik.loginrestapi.domain.agent.login.dto.LoginCommand;
import io.kwangsik.loginrestapi.domain.agent.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginApiController extends BaseApiController {
    private final LoginService service;

// TEST
@GetMapping("/api/agent/login/{loginId}")
public ResponseEntity<ApiOne> loginForTest(@PathVariable("loginId") String loginId, HttpSession httpSession) {
    log.debug("$$ LoginApiController.loginForTest() - loginId: " + loginId);
    return loginForAgent(loginId, "$2a$10$IpQpdvYynWy4.X7XiC2F7.jvmv.EPX5mQ6qDeu6YrVsuhNhaDuBjK", httpSession);
}
// TEST
@GetMapping("/api/token/login/{loginId}")
public ResponseEntity<ApiOne> testForGettingToken(@PathVariable("loginId") String loginId) {
    log.debug("$$ LoginApiController.testForGettingToken() - loginId: " + loginId);
    return getToken(loginId, "$2a$10$IpQpdvYynWy4.X7XiC2F7.jvmv.EPX5mQ6qDeu6YrVsuhNhaDuBjK");
}

    @PostMapping("/api/agent/login")
    public ResponseEntity<ApiOne> loginForAgent(@RequestBody LoginCommand command, HttpSession httpSession) {
        log.debug("$$ LoginApiController.loginForAgent() - LoginCommand: " + command);
        return loginForAgent(command.getLoginId(), command.getPassword(), httpSession);
    }

    @PostMapping("/api/token/login")
    public ResponseEntity<ApiOne> getToken(@RequestBody LoginCommand command) {
        log.debug("$$ LoginApiController.getToken() - LoginCommand: " + command);
        return getToken(command.getLoginId(), command.getPassword());
    }

    // private ----------
    private ResponseEntity<ApiOne> loginForAgent(String loginId, String password, HttpSession httpSession) {
        try {
            return ok("로그인이 완료되었습니다.", this.service.loginForAgent(loginId, password, httpSession));
        } catch (RuntimeException e) {
            return badRequest(e.getMessage());
        }
    }

private ResponseEntity<ApiOne> getToken(String loginId, String password) {
    try {
        return ok("로그인이 완료되었습니다.", this.service.getToken(loginId, password));
    } catch (RuntimeException e) {
        return badRequest(e.getMessage());
    }
}
}