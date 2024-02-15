package com.szs.restapi;

import com.szs.restapi.domain.user.UserEntity;
import com.szs.restapi.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void checkUser() throws Exception {

        Optional<UserEntity> optional = userRepository.findById("honggildong");
        Assertions.assertThat(optional).isPresent();

    }

    @Test
    public void signUp() throws Exception {

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId("user1234");
        userEntity.setPassword(encoder.encode("1234677"));
        userEntity.setName("홍길동");
        userEntity.setRegNo("860824-1655068");

        userRepository.save(userEntity);

    }


}
