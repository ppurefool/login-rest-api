package io.kwangsik.loginrestapi.domain.agent.login.dto;

import lombok.Getter;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Getter
@SuppressWarnings({"UnusedDeclaration"})
public class LoginCommand {
    private String loginId;
    private String password;
}