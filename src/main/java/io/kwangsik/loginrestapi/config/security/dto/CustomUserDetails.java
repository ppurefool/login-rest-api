package io.kwangsik.loginrestapi.config.security.dto;

import io.kwangsik.loginrestapi.domain.user.dto.UserVO;
import io.kwangsik.loginrestapi.util.ConstantUtil;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@ToString(doNotUseGetters = true)
@Getter
public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final LocalDateTime passwordChangeDateTime;
    private final String lockYn;

    public CustomUserDetails(UserVO vo) {
        this.username = vo.getLoginId();
//        this.password = vo.getPassword();
        this.password = "{noop}" + vo.getPassword();
        this.passwordChangeDateTime = vo.getPasswordChangeDateTime();
        this.lockYn = vo.getLockYn();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !(ConstantUtil.Y.equals(this.lockYn));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.passwordChangeDateTime.isBefore(LocalDateTime.now().minusDays(90));
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}