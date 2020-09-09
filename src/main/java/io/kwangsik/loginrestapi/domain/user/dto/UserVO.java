package io.kwangsik.loginrestapi.domain.user.dto;

import io.kwangsik.loginrestapi.domain.user.entity.User;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString(doNotUseGetters = true)
@Getter
public class UserVO {
    private final String loginId;
    private final String employeeNumber;
    private final String userName;
    private final String phoneNumber;
    private final String mobilePhoneNumber;
    private final String email;
    private final String positionId;
    private final String dutyId;
    private final String password;
    private final String passwordChangeYn;
    private final LocalDateTime passwordChangeDateTime;
    private final String serveStatusCode;
    private final LocalDate registrationDateTime;
    private final String registrationLoginId;
    private final LocalDate lastChangeDateTime;
    private final String lastChangeLoginId;
    private final Integer loginFailCount;
    private final String lockYn;

    public UserVO(User entity) {
        this.loginId = entity.getLoginId();
        this.employeeNumber = entity.getEmployeeNumber();
        this.userName = entity.getUserName();
        this.phoneNumber = entity.getPhoneNumber();
        this.mobilePhoneNumber = entity.getMobilePhoneNumber();
        this.email = entity.getEmail();
        this.positionId = entity.getPositionId();
        this.dutyId = entity.getDutyId();
        this.password = entity.getPassword();
        this.passwordChangeYn = entity.getPasswordChangeYn();
        this.passwordChangeDateTime = entity.getPasswordChangeDateTime();
        this.serveStatusCode = entity.getServeStatusCode();
        this.registrationDateTime = entity.getRegistrationDateTime();
        this.registrationLoginId = entity.getRegistrationLoginId();
        this.lastChangeDateTime = entity.getLastChangeDateTime();
        this.lastChangeLoginId = entity.getLastChangeLoginId();
        this.loginFailCount = entity.getLoginFailCount();
        this.lockYn = entity.getLockYn();
    }
}