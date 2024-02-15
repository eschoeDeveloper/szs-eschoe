package com.szs.restapi.domain.scrap;

import com.szs.restapi.globals.component.CryptoFieldConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Convert;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrapDTO {

    @Schema(description = "이름", requiredMode = Schema.RequiredMode.REQUIRED, example = "김OO")
    private String 이름;

    @Schema(description = "주민등록번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "123678-12345607")
    @Convert(converter = CryptoFieldConverter.class)
    private String 주민등록번호;

    @Schema(description = "총지급액", requiredMode = Schema.RequiredMode.REQUIRED, example = "60,000,000")
    private String 총지급액;

    @Schema(description = "산출세액", requiredMode = Schema.RequiredMode.REQUIRED, example = "100,000")
    private String 산출세액;

    @Schema(description = "보험료", requiredMode = Schema.RequiredMode.REQUIRED, example = "100,000")
    private String 보험료;

    @Schema(description = "교육비", requiredMode = Schema.RequiredMode.REQUIRED, example = "100,000")
    private String 교육비;

    @Schema(description = "기부금", requiredMode = Schema.RequiredMode.REQUIRED, example = "100,000")
    private String 기부금;

    @Schema(description = "의료비", requiredMode = Schema.RequiredMode.REQUIRED, example = "100,000")
    private String 의료비;

    @Schema(description = "퇴직연금", requiredMode = Schema.RequiredMode.REQUIRED, example = "1,000,000")
    private String 퇴직연금;

}
