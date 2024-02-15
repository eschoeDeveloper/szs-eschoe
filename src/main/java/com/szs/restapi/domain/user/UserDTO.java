package com.szs.restapi.domain.user;

import com.szs.restapi.globals.component.CryptoFieldConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Convert;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

    @Schema(description = "사용자 아이디", requiredMode = Schema.RequiredMode.REQUIRED, example = "id")
    private String userId;
    @Schema(description = "사용자 비밀번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "password")
    private String password;
    @Schema(description = "사용자 이름", requiredMode = Schema.RequiredMode.AUTO, example = "name")
    private String name;
    @Schema(description = "사용자 주민등록번호", requiredMode = Schema.RequiredMode.AUTO, example = "regno")
    @Convert(converter = CryptoFieldConverter.class)
    private String regNo;
    @Schema(description = "사용자 토큰", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "accessToken")
    private String accessToken;

}
