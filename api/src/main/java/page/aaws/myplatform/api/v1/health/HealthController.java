package page.aaws.myplatform.api.v1.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@Tag(name = "Health Check", description = "서버 상태 확인 및 에코 테스트 API")
@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @Operation(summary = "서버 상태 확인", description = "서버가 정상적으로 동작 중인지 확인합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Map.class),
                    examples = @ExampleObject(value = "{\"status\": \"OK\", \"timestamp\": \"2024-01-01T00:00:00Z\"}")))
    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "OK",
                "timestamp", Instant.now().toString()
        );
    }

    @Operation(summary = "에코 테스트", description = "요청받은 데이터를 그대로 반환합니다. (인증 필요)")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "401", description = "인증 실패", content=@Content)
    @ApiResponse(responseCode = "403", description = "권한 없음", content=@Content)
    @PostMapping("/echo")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'PARTNER', 'ADMIN')")
    public Map<String, Object> echo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "에코할 데이터", required = true,
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"message\": \"Hello\"}")))
            @RequestBody Map<String, Object> body) {
        return Map.of("status", "OK", "received", body);
    }
}