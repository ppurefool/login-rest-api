package io.kwangsik.loginrestapi.domain.user.dto;

import io.kwangsik.loginrestapi.util.CheckUtil;
import lombok.Getter;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Getter
@SuppressWarnings({"UnusedDeclaration"})
public class UserQuery {
    private String userName;

    public boolean hasUserName() {
        return CheckUtil.isNotBlank(this.userName);
    }
}