package com.szs.restapi;

import com.szs.restapi.domain.user.whitelist.UserWhiteListDTO;
import com.szs.restapi.domain.user.whitelist.UserWhiteListEntity;
import com.szs.restapi.domain.user.whitelist.UserWhiteListInterface;
import com.szs.restapi.domain.user.whitelist.UserWhiteListRepository;
import com.szs.restapi.globals.component.CryptoComponent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserWhiteListTest {

    @Autowired
    private UserWhiteListRepository userWhiteListRepository;

    @Test
    public void checkUserWhiteList() throws Exception {

        UserWhiteListDTO userWhiteListDTO = new UserWhiteListDTO();

        // 존재하는 사용자
        userWhiteListDTO.setName("홍길동");
        userWhiteListDTO.setRegNo("860824-1655068");

        Optional<UserWhiteListEntity> entity = userWhiteListRepository.findById(userWhiteListDTO.getRegNo());
        Assertions.assertThat(entity).isPresent();

        // 존재하지 않는 사용자
        userWhiteListDTO.setName("이지금");
        userWhiteListDTO.setRegNo("940114-1625102");

        Optional<UserWhiteListEntity> entity2 = userWhiteListRepository.findById(userWhiteListDTO.getRegNo());
        Assertions.assertThat(entity2).isPresent();

    }

}
