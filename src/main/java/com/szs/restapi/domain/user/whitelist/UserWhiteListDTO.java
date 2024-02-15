package com.szs.restapi.domain.user.whitelist;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserWhiteListDTO {

    @Schema(description = "사용자 이름", requiredMode = Schema.RequiredMode.AUTO, example = "daeaeaezezezfzxfzx")
    private String name;
    @Schema(description = "사용자 주민등록번호", requiredMode = Schema.RequiredMode.AUTO, example = "daeaeaezezezfzxfzx")
    private String regNo;


}
