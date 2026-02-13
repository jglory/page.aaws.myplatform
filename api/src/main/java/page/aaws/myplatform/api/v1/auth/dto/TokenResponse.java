package page.aaws.myplatform.api.v1.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Schema(description = "인증 토큰 응답")
public record TokenResponse<T>(
        @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9...")
        String accessToken,

        @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9...")
        String refreshToken,

        @Schema(description = "권한 범위", example = "read write")
        String scope,

        @Schema(description = "토큰 타입", example = "Bearer")
        String tokenType,

        @Schema(description = "추가 정보")
        T additionalInfo
) {
    public static TokenResponse<Void> of(String accessToken, String refreshToken, String scope, String tokenType) {
        return new TokenResponse<>(accessToken, refreshToken, scope, tokenType, null);
    }
}