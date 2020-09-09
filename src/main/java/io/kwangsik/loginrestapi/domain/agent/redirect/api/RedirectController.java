package io.kwangsik.loginrestapi.domain.agent.redirect.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    @Value("${server.servlet.session.cookie.name}")
    private String tokenName;

    @GetMapping("/api/agent/redirect/{token}")
    public ResponseEntity<String> redirect(@PathVariable("token") String token, @RequestParam("uri") String uri) {
        final HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.SET_COOKIE, tokenName.concat("=").concat(token).concat("; Path=/; HttpOnly"));
        headers.add(HttpHeaders.LOCATION, uri);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}