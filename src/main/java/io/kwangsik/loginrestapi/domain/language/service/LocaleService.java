package io.kwangsik.loginrestapi.domain.language.service;

import io.kwangsik.loginrestapi.domain.language.dto.LanguageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocaleService {
    private final LanguageService service;

    @Value("${solupia.language.code}")
    private String languageCode;

    public String get(String messageKey) {
        log.debug("$$ LocaleService.get() - messageKey: " + messageKey);
        final LanguageVO vo = this.service.findFirstByKoreanNameAndLanguageCode(messageKey, this.languageCode);

        return (null != vo && vo.hasData() ? vo.getLanguageName() : messageKey);
    }
}