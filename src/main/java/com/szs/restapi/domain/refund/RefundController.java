package com.szs.restapi.domain.refund;

import com.szs.restapi.globals.security.SzsUserDetails;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 결정세액 조회 API", description = "사용자 결정세액을 조회하기 위한 API입니다.")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/szs")
public class RefundController {

    private final RefundService refundService;

    @Operation(summary = "결정세액 조회", description = "사용자별로 결정세액을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class))),
            @ApiResponse(responseCode = "401", description = "인가 기능이 확인되지 않은 접근"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"
                    , content = @Content(schema = @Schema(implementation = RefundDTO.class)))
    })
    @PostMapping(value="/refund", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refund (@RequestHeader("Authorization") String accessToken, @AuthenticationPrincipal SzsUserDetails userDetails) throws Exception {
        return ResponseEntity.ok(refundService.execute(accessToken, userDetails));
    }

}
