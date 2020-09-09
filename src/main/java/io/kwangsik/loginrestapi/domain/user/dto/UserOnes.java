package io.kwangsik.loginrestapi.domain.user.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString(doNotUseGetters = true)
@Getter
@SuppressWarnings({"UnusedDeclaration"})
public class UserOnes {
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
}