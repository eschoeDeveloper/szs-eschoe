package com.szs.restapi;

import com.szs.restapi.domain.refund.component.RefundCaculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RefundCalculateTest {

    private RefundCaculator refundCaculator;

    @BeforeEach
    public void beforeEach() {
        refundCaculator = new RefundCaculator();
    }

    // 결정세액
    @Test
    void 결정세액() {

        String 산출세액 = "1,000,000";
        String 근로소득공제금액 = "150,000";
        String 특별세액공제금액 = "120,000";
        String 표준세액공제금액 = "0";
        String 퇴직연금세액공제금액 = "140,000";

        Assertions.assertThat(refundCaculator.결정세액(산출세액, 근로소득공제금액, 특별세액공제금액, 표준세액공제금액, 퇴직연금세액공제금액)).isNotNull();
    }

    // 근로소득세액공제금액
    @Test
    void 근로소득세액공제금액() {
        Assertions.assertThat(refundCaculator.근로소득세액공제금액("1,000,000")).isNotNull();
    }

    // 특별세액공제금액
    @Test
    void 특별세액공제금액() {

        int 보험료공제금액 = refundCaculator.보험료공제금액("350,000");
        int 의료비공제금액 = refundCaculator.의료비공제금액("290,000", "70,000,000");
        int 교육비공제금액 = refundCaculator.교육비공제금액("135,000");
        int 기부금공제금액 = refundCaculator.기부금공제금액("3,130,000");

        Assertions.assertThat(refundCaculator.특별세액공제금액(보험료공제금액, 의료비공제금액, 교육비공제금액, 기부금공제금액)).isNotNull();
    }

    // 보험료공제금액
    @Test
    void 보험료공제금액() {
        Assertions.assertThat(refundCaculator.보험료공제금액("350,000")).isNotNull();
    }

    // 의료비공제금액
    @Test
    void 의료비공제금액() {
        Assertions.assertThat(refundCaculator.의료비공제금액("290,000", "70,000,000")).isNotNull();
    }

    // 교육비공제금액
    @Test
    void 교육비공제금액() {
        Assertions.assertThat(refundCaculator.교육비공제금액("135,000")).isNotNull();
    }

    // 기부금공제금액
    @Test
    void 기부금공제금액() {
        Assertions.assertThat(refundCaculator.기부금공제금액("3,130,000")).isNotNull();
    }

    // 표준세액공제금액
    @Test
    void 표준세액공제금액() {

        int 보험료공제금액 = refundCaculator.보험료공제금액("350,000");
        int 의료비공제금액 = refundCaculator.의료비공제금액("290,000", "70,000,000");
        int 교육비공제금액 = refundCaculator.교육비공제금액("135,000");
        int 기부금공제금액 = refundCaculator.기부금공제금액("3,130,000");

        String 특별세액공제금액 = refundCaculator.특별세액공제금액(보험료공제금액, 의료비공제금액, 교육비공제금액, 기부금공제금액);

        Assertions.assertThat(refundCaculator.표준세액공제금액(특별세액공제금액)).isNotNull();
    }

    // 퇴직연금세액공제금액
    @Test
    void 퇴직연금세액공제금액() {
        Assertions.assertThat(refundCaculator.퇴직연금세액공제금액("6,900,000")).isNotNull();
    }


}
