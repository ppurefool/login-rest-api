package io.kwangsik.loginrestapi.domain.user.api;

import io.kwangsik.loginrestapi.common.ApiOne;
import io.kwangsik.loginrestapi.common.BaseApiController;
import io.kwangsik.loginrestapi.domain.user.dto.UserQuery;
import io.kwangsik.loginrestapi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController extends BaseApiController {
    private final UserService service;

    @GetMapping("/api/users")
    public ResponseEntity<ApiOne> findOnesBy(UserQuery query) {
        log.debug("$$ UserApiController.findAllBy() - UserQuery: " + query);
        return ok("조회가 완료되었습니다.", this.service.findOnesBy(query));
    }

    @GetMapping("/api/user/{loginId}")
    public ResponseEntity<ApiOne> findOneBy(@PathVariable("loginId") String loginId) {
        log.debug("$$ UserApiController.findOneBy() - loginId: " + loginId);
        try {
            return ok("조회가 완료되었습니다.", this.service.findOneBy(loginId));
        } catch (RuntimeException e) {
            return badRequest(e.getMessage());
        }
    }
}