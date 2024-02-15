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

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("scrapService")
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService {

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final RestAPIConnector restAPIConnector;

    @Override
    public Map<String, Object> execute( String accessToken, SzsUserDetails userDetails) throws Exception {

        Optional<UserEntity> optional =  userRepository.findById(userDetails.getUsername());

        if(!optional.isPresent()) throw new RuntimeException("존재하지 않는 사용자입니다.");

        UserEntity userEntity = optional.get();

        final String name = userEntity.getName();
        final String regNo = userEntity.getRegNo();

        Map<String, Object> httpParams = new HashMap<>();

        httpParams.put("accessToken", accessToken);
        httpParams.put("name", name);
        httpParams.put("regNo", regNo);

        // 스크래핑 데이터 요청
        JSONObject resultMap = restAPIConnector.connect("https://codetest.3o3.co.kr/v2/scrap", HttpMethod.POST, httpParams);

        // 스크래핑 후 DB 저장
        if(resultMap.getString("status").equals("success") && resultMap.has("data")) {

            final String workCostColumn = "총지급액";
            final String taxTypeColumn = "소득구분";
            final String[] taxValues = new String[] {"금액", "총납임금액"};
            final String[] taxKeys = new String[] {"보험료", "교육비", "기부금", "의료비", "퇴직연금"};

            JSONObject data = resultMap.getJSONObject("data");
            JSONObject jsonList = data.getJSONObject("jsonList");
            JSONArray workCostList = jsonList.getJSONArray("급여");
            JSONArray taxList = jsonList.getJSONArray("소득공제");

            String taxAmount = jsonList.getString("산출세액");
            
            // 총지급액 여러개일 경우를 대비한 계산
            int calculateTotalWorkCost = 0;
            for(Object workCostObject: workCostList) {

                JSONObject workCostJson = (JSONObject) workCostObject;

                String workCost = workCostJson.getString(workCostColumn).replaceAll(",", "");
                calculateTotalWorkCost += Integer.parseInt(workCost);

            }

            String totalWorkCost = new DecimalFormat("#,###").format(calculateTotalWorkCost);

            ScrapEntity scrapEntity = new ScrapEntity();

            scrapEntity.set총지급액(totalWorkCost);
            scrapEntity.set이름(name);
            scrapEntity.set주민등록번호(regNo);
            scrapEntity.set산출세액(taxAmount);

            for(Object taxJsonObject: taxList) {

                JSONObject taxJson = (JSONObject) taxJsonObject;

                for(String taxKey: taxKeys) {

                    if(taxJson.has(taxTypeColumn) && taxJson.getString(taxTypeColumn).equals(taxKey)) {

                        for(String taxValue : taxValues) {
                            if(taxJson.has(taxValue)) {
                                Method method = ScrapEntity.class.getMethod("set" + taxKey, String.class);
                                method.invoke(scrapEntity, taxJson.getString(taxValue));
                            }
                        }

                    }

                }

            }

            scrapRepository.save(scrapEntity);

        } else {
            throw new RuntimeException("스크래핑 데이터 가져오기 실패");
        }

        return resultMap.toMap();

    }

}
