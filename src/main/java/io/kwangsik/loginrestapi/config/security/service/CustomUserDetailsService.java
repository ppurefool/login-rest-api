package io.kwangsik.loginrestapi.config.security.service;

import io.kwangsik.loginrestapi.config.security.dto.CustomUserDetails;
import io.kwangsik.loginrestapi.domain.user.dto.UserVO;
import io.kwangsik.loginrestapi.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserVO userVO;

        try {
            userVO = this.userService.findVOBy(username);
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new CustomUserDetails(userVO);
    }
}