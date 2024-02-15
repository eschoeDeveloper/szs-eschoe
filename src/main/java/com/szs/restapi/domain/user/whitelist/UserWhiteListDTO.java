package com.szs.restapi.domain.user.whitelist;

import com.szs.restapi.globals.component.CryptoFieldConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Convert;
import lombok.Data;

@Data
public class UserWhiteListDTO {

    @Schema(description = "사용자 이름", requiredMode = Schema.RequiredMode.AUTO, example = "daeaeaezezezfzxfzx")
    private String name;

    @Schema(description = "사용자 주민등록번호", requiredMode = Schema.RequiredMode.AUTO, example = "daeaeaezezezfzxfzx")
    @Convert(converter = CryptoFieldConverter.class)
    private String regNo;


}
