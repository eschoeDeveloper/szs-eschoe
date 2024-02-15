package com.szs.restapi.domain.user;

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

@Tag(name = "사용자 회원가입, 로그인 API", description = "사용자의 회원가입 및 로그인을 제공하는 API")
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/szs")
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 로그인", description = "사용자의 로그인을 실행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "401", description = "인가 기능이 확인되지 않은 접근"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    @PostMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO)  {
        return ResponseEntity.ok(userService.login(userDTO));
    }

    @Operation(summary = "사용자 회원가입", description = "사용자 회원가입을 실행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "401", description = "인가 기능이 확인되지 않은 접근"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생"
                    , content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    @PostMapping(value="/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.signUp(userDTO));
    }

}
