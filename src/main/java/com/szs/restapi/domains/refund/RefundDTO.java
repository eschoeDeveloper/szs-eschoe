package com.szs.restapi.domains.refund;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
public class RefundDTO {

    @Schema(description = "이름", requiredMode = Schema.RequiredMode.REQUIRED, example = "김OO")
    private String 이름;
    @Schema(description = "결정세액", requiredMode = Schema.RequiredMode.REQUIRED, example = "100,000")
    private String 결정세액;
    @Schema(description = "퇴직연금세액공제", requiredMode = Schema.RequiredMode.REQUIRED, example = "20,000")
    private String 퇴직연금세액공제;

}
