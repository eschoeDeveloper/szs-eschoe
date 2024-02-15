package com.szs.restapi.globals.security;

import com.szs.restapi.domain.user.UserDTO;
import com.szs.restapi.domain.user.UserEntity;
import com.szs.restapi.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SzsUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("일치하는 사용자가 존재하지 않습니다."));

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setRegNo(user.getRegNo());

        return new SzsUserDetails(userDTO);

    }

}
