package com.szs.restapi.domains.scrap;

import com.szs.restapi.domains.refund.RefundDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "스크래핑 API", description = "사용자의 소득 정보를 외부 사이트에서 스크래핑하기 위한 API입니다.")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/szs")
public class ScrapController {

    private final ScrapService scrapService;

    @Operation(summary = "스크래핑 실행", description = "사용자의 소득 정보를 외부 사이트에서 스크래핑합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "스크래핑 성공"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class))),
            @ApiResponse(responseCode = "401", description = "인가 기능이 확인되지 않은 접근"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class)))
    })
    @PostMapping(value="/scrap", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> scrap (@RequestBody ScrapDTO scrapDTO) {
        return ResponseEntity.ok(scrapService.hashCode());
    }

}
