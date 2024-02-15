package com.szs.restapi.domain.refund;

import com.szs.restapi.globals.security.SzsUserDetails;

import java.util.Map;

public interface RefundService {
    Map<String, Object> execute(String accessToken, SzsUserDetails userDetails) throws Exception;
}
