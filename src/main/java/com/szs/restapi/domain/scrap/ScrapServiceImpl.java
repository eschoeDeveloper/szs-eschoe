package com.szs.restapi.domain.scrap;

import com.szs.restapi.domain.user.UserEntity;
import com.szs.restapi.domain.user.UserRepository;
import com.szs.restapi.globals.connector.RestAPIConnector;
import com.szs.restapi.globals.security.SzsUserDetails;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("scrapService")
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService {

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private final UserRepository userRepository;
    private final RestAPIConnector restAPIConnector;

    @Override
    public JSONObject execute( String accessToken, SzsUserDetails userDetails) throws Exception {

        Optional<UserEntity> optional =  userRepository.findById(userDetails.getUsername());

        if(!optional.isPresent()) throw new RuntimeException("존재하지 않는 사용자입니다.");

        UserEntity userEntity = optional.get();

        Map<String, Object> httpParams = new HashMap<>();

        httpParams.put("accessToken", accessToken);
        httpParams.put("name", userEntity.getName());
        httpParams.put("regNo", userEntity.getRegNo());

        JSONObject resultMap = restAPIConnector.connect("https://codetest.3o3.co.kr/v2/scrap", HttpMethod.POST, httpParams);

        System.out.println(resultMap.getString("status"));

        if(resultMap.getString("status").equals("success") && resultMap.has("data")) {

            JSONObject data = resultMap.getJSONObject("data");
            JSONObject jsonList = data.getJSONObject("jsonList");
            JSONArray workCostList = jsonList.getJSONArray("급여");
            JSONArray taxList = jsonList.getJSONArray("소득공제");

            JSONObject workCostJson = (JSONObject) workCostList.get(0);
            JSONObject taxListJson = (JSONObject) taxList.get(0);

            String taxAmount = jsonList.getString("산출세액");

            System.out.println(jsonList);
            System.out.println(workCostList);
            System.out.println(taxAmount);
            System.out.println(taxList);


        } else {
            throw new RuntimeException("스크래핑 데이터 가져오기 실패");
        }

        return resultMap;

    }

}
