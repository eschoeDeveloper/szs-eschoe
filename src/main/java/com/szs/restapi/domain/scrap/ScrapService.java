package com.szs.restapi.domain.scrap;

import com.szs.restapi.globals.security.SzsUserDetails;

import java.util.Map;

public interface ScrapService {

    /**
     * 스크래핑 실행
     *
     * @param accessToken the access token
     * @param userDetails the user details
     * @return the map
     * @throws Exception the exception
     */
    Map<String, Object> execute(String accessToken, SzsUserDetails userDetails) throws Exception;
}
