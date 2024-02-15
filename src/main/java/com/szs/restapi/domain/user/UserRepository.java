package com.szs.restapi.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Object> {

//    @Query(value = "SELECT DISTINCT a.* FROM SZS_USER a WHERE a.userId = :#{#requestUser.getUserId()}")
//    List<UserEntity> checkLoginUser(@Param("requestUser") UserDTO requestUser);
//
//    @Query(value = "SELECT DISTINCT a.* FROM SZS_USER a WHERE a.userId = :#{#requestUser.getUserId()}")
//    List<UserEntity> checkUser(@Param("requestUser") UserDTO requestUser);

}
