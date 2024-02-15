package com.szs.restapi.domain.refund;

import com.szs.restapi.globals.security.SzsUserDetails;

import java.util.Map;

public interface RefundService {

    /**
     * 결정세액 조회
     *
     * @param accessToken the access token
     * @param userDetails the user details
     * @return the map
     * @throws Exception the exception
     */
    Map<String, Object> execute(String accessToken, SzsUserDetails userDetails) throws Exception;
}
