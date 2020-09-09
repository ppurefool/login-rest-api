package io.kwangsik.loginrestapi.domain.language.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LANG")
@ToString(doNotUseGetters = true)
@Getter
public class Language {
    @Id
    @Column(name = "SEQ")
    private Long sequence;

    @Column(name = "LANG_CODE")
    private String languageCode;

    @Column(name = "KRN_NAME")
    private String koreanName;

    @Column(name = "LANG_NAME")
    private String languageName;

    @Column(name = "NOTE_NM")
    private String noteName;
}