package io.kwangsik.loginrestapi.domain.user.dao;

import io.kwangsik.loginrestapi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByUserNameContains(String userName);
}