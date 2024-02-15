package com.szs.restapi.domain.refund.component;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component("refundCacultor")
public class RefundCaculator {

    public int 보험료공제금액(String 보험료) {

        보험료 = 보험료.replaceAll(",", "");
        int insuranceCost = Integer.parseInt(보험료);
        return ( insuranceCost * 12 ) / 100;

    }

    public int 의료비공제금액(String 의료비, String 총급여) {

        의료비 = 의료비.replaceAll(",", "");
        총급여 = 총급여.replaceAll(",", "");
        int medicalCost = Integer.parseInt(의료비);
        int totalWorkCost = Integer.parseInt(총급여);
        int 보험료공제금액 = (( medicalCost - ((totalWorkCost * 3)/100) ) * 15) / 100;
        return (보험료공제금액 < 0) ? 0 : 보험료공제금액;

    }

    public int 교육비공제금액(String 교육비) {

        교육비 = 교육비.replaceAll(",", "");
        int educationCost = Integer.parseInt(교육비);
        return (educationCost * 15) / 100;

    }

    public int 기부금공제금액(String 기부금) {

        기부금 = 기부금.replaceAll(",", "");
        int donationCost = Integer.parseInt(기부금);
        return (donationCost * 15) / 100;

    }

    public String 특별세액공제금액(int 보험료공제금액, int 의료비공제금액, int 교육비공제금액, int 기부금공제금액) {
        int specialTaxCost = 보험료공제금액 + 의료비공제금액 + 교육비공제금액 + 기부금공제금액;
        return new DecimalFormat("#,###").format(specialTaxCost);
    }

    public String 근로소득세액공제금액(String 산출세액) {
        산출세액 = 산출세액.replaceAll(",", "");
        int extractTaxCost = Integer.parseInt(산출세액);
        double 근로소득공제금액 = (extractTaxCost * 0.55) / 100;
        return new DecimalFormat("#,###").format(근로소득공제금액);
    }

    public String 표준세액공제금액(String 특별세액공제금액) {

        특별세액공제금액 = 특별세액공제금액.replaceAll(",", "");

        int specialTaxCost = Integer.parseInt(특별세액공제금액);
        int defaultTaxCost = 0;

        if(specialTaxCost < 130000) {
            defaultTaxCost = 130000;
        }

        return new DecimalFormat("#,###").format(defaultTaxCost);

    }
    
    public String 퇴직연금세액공제금액(String 퇴직연금) {
        퇴직연금 = 퇴직연금.replaceAll(",", "");
        int retirementCost = Integer.parseInt(퇴직연금);
        double 퇴직연금세액공제금액 = (retirementCost * 0.15) / 100;
        return new DecimalFormat("#,###").format(퇴직연금세액공제금액);
    }

    public String 결정세액(String 산출세액, String 근로소득세액공제금액, String 특별세액공제금액, String 표준세액공제금액, String 퇴직연금세액공제금액) {

        산출세액 = 산출세액.replaceAll(",", "");
        근로소득세액공제금액 = 근로소득세액공제금액.replaceAll(",", "");
        특별세액공제금액 = 특별세액공제금액.replaceAll(",", "");
        표준세액공제금액 = 표준세액공제금액.replaceAll(",", "");
        퇴직연금세액공제금액 = 퇴직연금세액공제금액.replaceAll(",", "");

        int taxAmountCost = Integer.parseInt(산출세액);
        int extractTaxCost = Integer.parseInt(근로소득세액공제금액);
        int specialTaxCost = Integer.parseInt(특별세액공제금액);
        int defaultTaxCost = Integer.parseInt(표준세액공제금액);
        int retirementCost = Integer.parseInt(퇴직연금세액공제금액);

        int determinedCost = taxAmountCost - extractTaxCost - specialTaxCost - defaultTaxCost - retirementCost;

        if(determinedCost < 0) {
            determinedCost = 0;
        }

        return new DecimalFormat("#,###").format(determinedCost);


    }

}
