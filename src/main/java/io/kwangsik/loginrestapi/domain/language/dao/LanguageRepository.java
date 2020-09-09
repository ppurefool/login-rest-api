package io.kwangsik.loginrestapi.domain.language.dao;

import io.kwangsik.loginrestapi.domain.language.entity.Language;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    @SuppressWarnings("all")
    @Cacheable("localeMessageCache")
    Language findFirstByKoreanNameAndLanguageCode(String koreanName, String languageCode);
}