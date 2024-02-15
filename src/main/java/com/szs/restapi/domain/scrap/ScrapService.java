package com.szs.restapi.domain.scrap;

import com.szs.restapi.globals.security.SzsUserDetails;
import org.json.JSONObject;

public interface ScrapService {

    JSONObject execute(String accessToken, SzsUserDetails userDetails) throws Exception;

}
