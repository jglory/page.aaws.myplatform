package page.aaws.myplatform.api.v1.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import page.aaws.myplatform.api.v1.auth.dto.LoginRequest;

import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ObjectMapper objectMapper;

    @Operation(
        summary = "로그인",
        description = "사용자 이메일과 비밀번호를 사용하여 로그인합니다. 성공 시 JWT 토큰을 반환합니다.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "로그인 요청 정보",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = LoginRequest.class)
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "로그인 성공",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppResponse.class),
                    examples = @ExampleObject(
                        value = "{\n" +
                                "    \"success\": true,\n" +
                                "    \"status\": 1,\n" +
                                "    \"data\": {\n" +
                                "        \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9...\",\n" +
                                "        \"refreshToken\": \"eyJhbGciOiJIUzI1NiJ9...\",\n" +
                                "        \"scope\": \"read write\",\n" +
                                "        \"tokenType\": \"Bearer\"\n" +
                                "    },\n" +
                                "    \"error\": null,\n" +
                                "    \"timestamp\": \"2026-01-18 15:56:59\"\n" +
                                "}"
                    )
                )
            ),
            @ApiResponse(
                responseCode = "401", 
                description = "인증 실패",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppResponse.class),
                    examples = @ExampleObject(
                        value = "{\n" +
                                "  \"success\": false,\n" +
                                "  \"status\": 1004,\n" +
                                "  \"data\": null,\n" +
                                "  \"error\": {\n" +
                                "    \"code\": 1004,\n" +
                                "    \"message\": \"인증에 실패하였습니다.\",\n" +
                                "    \"timestamp\": \"2026-01-18 16:03:10\"\n" +
                                "  },\n" +
                                "  \"timestamp\": \"2026-01-18 16:03:10\"\n" +
                                "}"
                    )
                )
            )
        }
    )
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
        // 이 메서드는 Spring Security 필터에 의해 처리되므로 실제 로직은 실행되지 않습니다.
        // Swagger 문서화를 위해 존재합니다.
    }

    @Operation (
        summary = "액세스 토큰 재발급",
        description = "refresh token을 사용하여 새로운 access token, refresh token을 발급 받습니다.",
        responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "재발급 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AppResponse.class),
                            examples = @ExampleObject(
                                value = "{\n" +
                                        "    \"success\": true,\n" +
                                        "    \"status\": 1,\n" +
                                        "    \"data\": {\n" +
                                        "        \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9...\",\n" +
                                        "        \"refreshToken\": \"eyJhbGciOiJIUzI1NiJ9...\",\n" +
                                        "        \"scope\": \"read write\",\n" +
                                        "        \"tokenType\": \"Bearer\"\n" +
                                        "    },\n" +
                                        "    \"error\": null,\n" +
                                        "    \"timestamp\": \"2026-01-18 15:56:59\"\n" +
                                        "}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패")
        }
    )
    @PostMapping("/reissue")
    public void reissue() {
        // 이 메서드는 Spring Security 필터에 의해 처리되므로 실제 로직은 실행되지 않습니다.
        // Swagger 문서화를 위해 존재합니다.
    }

    @Operation(
        summary = "이메일 중복 확인", 
        description = "회원가입 시 이메일이 이미 존재하는지 확인합니다.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "사용 가능한 이메일",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppResponse.class)
                )
            ),
            @ApiResponse(
                responseCode = "409",
                description = "이미 존재하는 이메일",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AppResponse.class)
                )
            )
        }
    )
    @GetMapping("/check-email/{email}")
    public ResponseEntity<AppResponse<Void>> checkEmail(
        @Parameter(description = "중복 확인할 이메일", example = "user@example.com") 
        @PathVariable String email
    ) {
//        if (true) {
//            throw new AppException(HttpStatus.CONFLICT, AppStatus.NOT_AVAILABLE, "이미 존재하는 이메일 입니다.");
//        }
        return ResponseEntity.status(HttpStatus.OK).body(AppResponse.success(AppStatus.OK)); // 중복되지 않음
    }
}
