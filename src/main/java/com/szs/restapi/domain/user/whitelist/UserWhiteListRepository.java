package com.szs.restapi.domain.user.whitelist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserWhiteListRepository extends JpaRepository<UserWhiteListEntity, Object> {

    @Query("SELECT name, regNo FROM SZS_USER_WHITELIST WHERE name = :#{#requestUser.name} and regNo = :#{#requestUser.regNo}")
    List<UserWhiteListInterface> checkUserWhiteList(@Param("requestUser") UserWhiteListDTO requestUser);

}
