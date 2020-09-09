package io.kwangsik.loginrestapi.domain.user.dto;

import io.kwangsik.loginrestapi.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Getter
public class UserOne {
    private String loginId;
    private String employeeNumber;
    private String userName;
    private String phoneNumber;
    private String mobilePhoneNumber;
    private String email;
    private String positionId;
    private String dutyId;
    private String passwordChangeYn;
    private LocalDateTime passwordChangeDateTime;
    private String serveStatusCode;
    private LocalDate registrationDateTime;
    private String registrationLoginId;
    private LocalDate lastChangeDateTime;
    private String lastChangeLoginId;
    private Integer loginFailCount;
    private String lockYn;

    public UserOne(User entity) {
        this.loginId = entity.getLoginId();
        this.employeeNumber = entity.getEmployeeNumber();
        this.userName = entity.getUserName();
        this.phoneNumber = entity.getPhoneNumber();
        this.mobilePhoneNumber = entity.getMobilePhoneNumber();
        this.email = entity.getEmail();
        this.positionId = entity.getPositionId();
        this.dutyId = entity.getDutyId();
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