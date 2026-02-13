package page.aaws.myplatform.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "에러 응답")
public class AppError {
    @Schema(description = "에러 코드", example = "1005")
    private final int code;

    @Schema(description = "에러 메시지", example = "잘못된 형식입니다.")
    private final String message;

    @Schema(description = "응답 시간", example = "2024-01-01 12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    /**
     * AppStatusCode로부터 에러 응답 생성
     *
     * @param status 상태 코드
     * @return 에러 응답
     */
    public static AppError of(AppStatusCode status) {
        return AppError.builder()
                .code(status.value())
                .message(status.message())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * AppStatusCode와 커스텀 메시지로 에러 응답 생성
     *
     * @param status 상태 코드
     * @param message 커스텀 메시지
     * @return 에러 응답
     */
    public static AppError of(AppStatusCode status, String message) {
        return AppError.builder()
                .code(status.value())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
