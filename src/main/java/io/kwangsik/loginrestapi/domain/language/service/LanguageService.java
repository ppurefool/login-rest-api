package io.kwangsik.loginrestapi.domain.language.service;

import io.kwangsik.loginrestapi.common.BaseService;
import io.kwangsik.loginrestapi.domain.language.dao.LanguageRepository;
import io.kwangsik.loginrestapi.domain.language.dto.LanguageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 유광식
 * @since 2020-01-29
 */
@Service
@RequiredArgsConstructor
public class LanguageService extends BaseService {
    private final LanguageRepository repository;

    public LanguageVO findFirstByKoreanNameAndLanguageCode(String koreanName, String languageCode) {
        return map(this.repository.findFirstByKoreanNameAndLanguageCode(koreanName, languageCode), LanguageVO.class);
    }
}