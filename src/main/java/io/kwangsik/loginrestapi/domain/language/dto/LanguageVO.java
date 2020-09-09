package io.kwangsik.loginrestapi.domain.language.dto;

import lombok.Getter;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Getter
@SuppressWarnings({"UnusedDeclaration"})
public class LanguageVO {
    private Long sequence;
    private String languageCode;
    private String koreanName;
    private String languageName;
    private String noteName;

    public boolean hasData() {
        return (null != this.sequence);
    }
}