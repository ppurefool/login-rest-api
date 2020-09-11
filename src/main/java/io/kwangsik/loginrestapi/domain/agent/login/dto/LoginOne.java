package io.kwangsik.loginrestapi.domain.agent.login.dto;

import io.kwangsik.loginrestapi.domain.user.dto.UserVO;
import io.kwangsik.loginrestapi.util.ConstantUtil;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Consumer;

@ToString(doNotUseGetters = true)
@Getter
public class LoginOne {
    private final String loginId;
    private final String employeeNumber;
    private final String userName;
    private final String phoneNumber;
    private final String mobilePhoneNumber;
    private final String email;
    private final String positionId;
    private final String dutyId;
    private final String passwordChangeYn;
    private final LocalDateTime passwordChangeDateTime;
    private final String serveStatusCode;
    private final LocalDate registrationDateTime;
    private final String registrationLoginId;
    private final LocalDate lastChangeDateTime;
    private final String lastChangeLoginId;
    private final Integer loginFailCount;
    private final String lockYn;
    private String token;

    public LoginOne(String newLoginId, UserVO vo) {
        this.loginId = newLoginId;
        this.employeeNumber = vo.getEmployeeNumber();
        this.userName = vo.getUserName();
        this.phoneNumber = vo.getPhoneNumber();
        this.mobilePhoneNumber = vo.getMobilePhoneNumber();
        this.email = vo.getEmail();
        this.positionId = vo.getPositionId();
        this.dutyId = vo.getDutyId();
        this.passwordChangeYn = vo.getPasswordChangeYn();
        this.passwordChangeDateTime = vo.getPasswordChangeDateTime();
        this.serveStatusCode = vo.getServeStatusCode();
        this.registrationDateTime = vo.getRegistrationDateTime();
        this.registrationLoginId = vo.getRegistrationLoginId();
        this.lastChangeDateTime = vo.getLastChangeDateTime();
        this.lastChangeLoginId = vo.getLastChangeLoginId();
        this.loginFailCount = vo.getLoginFailCount();
        this.lockYn = vo.getLockYn();
    }

    public LoginOne updateToken(String newToken) {
        this.token = newToken;
        return this;
    }
}