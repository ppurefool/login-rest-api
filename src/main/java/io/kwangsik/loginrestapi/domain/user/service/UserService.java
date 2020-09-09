package io.kwangsik.loginrestapi.domain.user.service;

import io.kwangsik.loginrestapi.common.BaseService;
import io.kwangsik.loginrestapi.domain.user.dao.UserRepository;
import io.kwangsik.loginrestapi.domain.user.dto.UserOne;
import io.kwangsik.loginrestapi.domain.user.dto.UserOnes;
import io.kwangsik.loginrestapi.domain.user.dto.UserQuery;
import io.kwangsik.loginrestapi.domain.user.dto.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService extends BaseService {
    private final UserRepository repository;

    public List<UserOnes> findOnesBy(UserQuery query) {
        log.debug("$$ UserService.findOnesBy() - hasUserName(): " + query.hasUserName());
        return mapList((query.hasUserName() ?
                this.repository.findAllByUserNameContains(query.getUserName()) :
                this.repository.findAll()), UserOnes.class);
    }

    public UserVO findVOBy(String id) {
        log.debug("$$ UserService.findVOBy() - id: " + id);
        return new UserVO(this.repository.findById(id).orElseThrow(() ->
                new RuntimeException("이미 삭제된 건입니다.")));
    }

    public UserOne findOneBy(String id) {
        return new UserOne(this.repository.findById(id).orElseThrow(() ->
                new RuntimeException("이미 삭제된 건입니다.")));
    }
}