package com.szs.restapi.domain.scrap;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
public class ScrapDTO  implements Serializable {

    @Schema(description = "name", requiredMode = Schema.RequiredMode.REQUIRED, example = "김OO")
    private String name;
    @Schema(description = "regNo", requiredMode = Schema.RequiredMode.REQUIRED, example = "123678-12345607")
    private String regNo;

}
