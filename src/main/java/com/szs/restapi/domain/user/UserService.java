package com.szs.restapi.domain.user;

public interface UserService {

    /**
     * 사용자 로그인
     * @param userDto 
     * @return
     */
    UserDTO login(UserDTO userDto);

    /**
     * 사용자 회원가입
     * @param userDto 
     * @return
     */
    UserDTO signUp(UserDTO userDto);

}
