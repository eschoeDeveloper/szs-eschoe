package com.szs.restapi.domain.refund;

import com.szs.restapi.domain.refund.component.RefundCaculator;
import com.szs.restapi.domain.scrap.ScrapEntity;
import com.szs.restapi.domain.scrap.ScrapRepository;
import com.szs.restapi.domain.user.UserEntity;
import com.szs.restapi.domain.user.UserRepository;
import com.szs.restapi.globals.security.SzsUserDetails;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service("refundService")
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;

    private final RefundCaculator refundCaculator;

    @Override
    public Map<String, Object> execute(String accessToken, SzsUserDetails userDetails) throws Exception {

        // 사용자 정보 조회하기
        Optional<UserEntity> userOptional =  userRepository.findById(userDetails.getUsername());
        if(!userOptional.isPresent()) throw new RuntimeException("존재하지 않는 사용자입니다.");

        UserEntity userEntity = userOptional.get();

        Optional<ScrapEntity> scrapOptional = scrapRepository.findById(userEntity.getRegNo());
        if(!scrapOptional.isPresent()) throw new RuntimeException("스크래핑 데이터가 존재하지 않습니다.");

        ScrapEntity scrapEntity = scrapOptional.get();

        String 산출세액 = scrapEntity.get산출세액();
        String 의료비 = scrapEntity.get의료비();
        String 기부금 = scrapEntity.get기부금();
        String 보험료 = scrapEntity.get보험료();
        String 총지급액 = scrapEntity.get총지급액();
        String 퇴직연금 = scrapEntity.get퇴직연금();
        String 교육비 = scrapEntity.get교육비();

        // 특별세액공제금액 계산
        int 보험료공제금액 = refundCaculator.보험료공제금액(보험료);
        int 의료비공제금액 = refundCaculator.의료비공제금액(의료비, 총지급액);
        int 교육비공제금액 = refundCaculator.교육비공제금액(교육비);
        int 기부금공제금액 = refundCaculator.기부금공제금액(기부금);

        String 특별세액공제금액 = refundCaculator.특별세액공제금액(보험료공제금액, 의료비공제금액, 교육비공제금액, 기부금공제금액);

        // 근로소득세액공제금액 계산
        String 근로소득세액공제금액 = refundCaculator.근로소득세액공제금액(산출세액);

        // 표준세액공제금액 계산
        String 표준세액공제금액 = refundCaculator.표준세액공제금액(특별세액공제금액);
        String temp표준세액공제금액 = 표준세액공제금액.replaceAll(",", "");
        int defaultTaxCost = Integer.parseInt(temp표준세액공제금액);

        if(defaultTaxCost == 130000) {
            특별세액공제금액 = "0";
        }

        // 퇴직연금세액공제금액 계산
        String 퇴직연금세액공제금액 = refundCaculator.퇴직연금세액공제금액(퇴직연금);
               
        // 결정세액 계산
        String 결정세액 = refundCaculator.결정세액(산출세액, 근로소득세액공제금액, 특별세액공제금액, 표준세액공제금액, 퇴직연금세액공제금액);

        JSONObject responseJson = new JSONObject();

        responseJson.put("이름", userEntity.getName());
        responseJson.put("결정세액", 결정세액);
        responseJson.put("퇴직연금세액공제", 퇴직연금세액공제금액);

        return responseJson.toMap();

    }

}
