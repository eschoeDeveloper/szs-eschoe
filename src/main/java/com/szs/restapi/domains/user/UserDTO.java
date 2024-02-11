package com.szs.restapi.domains.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
public class UserDTO implements Serializable {

    @Schema(description = "사용자 아이디", requiredMode = Schema.RequiredMode.REQUIRED, example = "id")
    private String userId;
    @Schema(description = "사용자 비밀번호", requiredMode = Schema.RequiredMode.REQUIRED, example = "daeaeaezezezfzxfzx")
    private String password;
    @Schema(description = "사용자 이름", requiredMode = Schema.RequiredMode.AUTO, example = "daeaeaezezezfzxfzx")
    private String name;
    @Schema(description = "사용자 주민등록번호", requiredMode = Schema.RequiredMode.AUTO, example = "daeaeaezezezfzxfzx")
    private String regNo;

}
