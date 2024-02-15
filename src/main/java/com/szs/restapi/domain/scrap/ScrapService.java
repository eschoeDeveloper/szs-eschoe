package com.szs.restapi.domain.scrap;

import com.szs.restapi.globals.security.SzsUserDetails;

import java.util.Map;

public interface ScrapService {
    Map<String, Object> execute(String accessToken, SzsUserDetails userDetails) throws Exception;
}
