package io.kwangsik.loginrestapi.domain.user.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@ToString(doNotUseGetters = true)
@Getter
public class User {
    @Id
    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "EMPNO")
    private String employeeNumber;

    @Column(name = "USER_NM")
    private String userName;

    @Column(name = "PHONE_NO")
    private String phoneNumber;

    @Column(name = "MBL_PHONE_NO")
    private String mobilePhoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PSTN_ID")
    private String positionId;

    @Column(name = "DUTY_ID")
    private String dutyId;

    @Column(name = "PWD")
    private String password;

    @Column(name = "PWD_CHG_YN")
    private String passwordChangeYn;

    @Column(name = "PWD_CHG_DTM")
    private LocalDateTime passwordChangeDateTime;

    @Column(name = "SERVE_STATUS_CODE")
    private String serveStatusCode;

    @Column(name = "RGST_DTM")
    private LocalDate registrationDateTime;

    @Column(name = "RGST_LOGIN_ID")
    private String registrationLoginId;

    @Column(name = "LAST_CHG_DTM")
    private LocalDate lastChangeDateTime;

    @Column(name = "LAST_CHG_LOGIN_ID")
    private String lastChangeLoginId;

    @Column(name = "LOGIN_FAIL_CNT")
    private Integer loginFailCount;

    @Column(name = "LOCK_YN")
    private String lockYn;
}