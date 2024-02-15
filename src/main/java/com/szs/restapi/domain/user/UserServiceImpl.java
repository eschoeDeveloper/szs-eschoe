package com.szs.restapi.domain.user;

import com.szs.restapi.domain.user.whitelist.UserWhiteListDTO;
import com.szs.restapi.domain.user.whitelist.UserWhiteListService;
import com.szs.restapi.globals.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtTokenProvider provider;

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;
    private final UserWhiteListService userWhiteListService;

    /**
     * 로그인 서비스
     *
     * @param requestUser the user dto
     * @return the user dto
     * @throws Exception the exception
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO login(UserDTO requestUser) {

        Optional<UserEntity> optional = userRepository.findById(requestUser.getUserId());

        if( !optional.isPresent() ) {
            throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
        }

        UserEntity userEntity = optional.get();

        if( !encoder.matches(requestUser.getPassword(), userEntity.getPassword()) ) {
            throw new BadCredentialsException("사용자 비밀번호가 일치하지 않습니다.");
        }

        String jwtToken = provider.generateJwtToken(requestUser);

        requestUser.setAccessToken(jwtToken);

        return requestUser;

    }

    /**
     * 회원가입 서비스
     *
     * @param userDto the user dto
     * @return the user dto
     * @throws Exception the exception
     */
    @Override
    public UserDTO signUp(UserDTO userDto) {

        // =====================================================
        // [ 규칙 ]
        //  1. 특정 기재된 사용자만 회원가입이 가능하다.
        // =====================================================
        UserWhiteListDTO userWhiteListDTO = new UserWhiteListDTO();
        userWhiteListDTO.setName(userDto.getName());
        userWhiteListDTO.setRegNo(userDto.getRegNo());

        boolean checkUserWhiteList = userWhiteListService.checkUserWhiteList(userWhiteListDTO);

        // 기재된 사용자인지 확인 후 회원가입 실행
        if( !checkUserWhiteList ) throw new RuntimeException("회원가입이 불가능한 사용자입니다.");

        // 중복 가입 확인
        Optional<UserEntity> optional = userRepository.findById(userDto.getUserId());

//        if( !ObjectUtils.isEmpty(checkUser) ) {//&& !StringUtils.hasLength(checkUser.getUserId()) && !StringUtils.hasText(checkUser.getUserId()) ) {
//            throw new RuntimeException("이미 존재하는 사용자입니다.");
//        }

        if( optional.isPresent() ) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(userDto.getUserId());
        userEntity.setPassword(encoder.encode(userDto.getPassword()));
        userEntity.setName(userDto.getName());
        userEntity.setRegNo(userDto.getRegNo());

        userRepository.save(userEntity);

        return userDto;

    }

}
