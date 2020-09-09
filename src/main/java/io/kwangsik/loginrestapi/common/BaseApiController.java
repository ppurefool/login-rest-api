package io.kwangsik.loginrestapi.common;

import io.kwangsik.loginrestapi.domain.language.service.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class BaseApiController {
    private LocaleService localeService;

    @Autowired
    private void setLocaleService(LocaleService newLocaleService) {
        this.localeService = newLocaleService;
    }

    protected ResponseEntity<ApiOne> badRequest(String message) {
        return ResponseEntity.badRequest().body(new ApiOne(this.localeService.get(message)));
    }

    protected ResponseEntity<ApiOne> ok(String message, Object result) {
        return ResponseEntity.ok(new ApiOne(this.localeService.get(message), result));
    }
}