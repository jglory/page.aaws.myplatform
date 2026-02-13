package page.aaws.myplatform.global.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import page.aaws.myplatform.global.exception.AppError;
import page.aaws.myplatform.global.exception.AppStatusCode;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Schema(description = "공통 응답 객체")
public class AppResponse<T> {

    @Schema(description = "성공 여부", example = "true")
    private final boolean success;

    @Schema(description = "응답 상태 코드", example = "1")
    private final AppStatusCode status;

    @Schema(description = "응답 데이터")
    private final T data;

    @Schema(description = "에러 상세 정보 (실패 시)", example = "null")
    private final AppError error; // 에러 발생 시 상세 정보 (코드, 메시지 등)

    @Schema(description = "응답 시간", example = "2024-01-01 12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    private AppResponse(boolean success, AppStatusCode status, T data, AppError error) {
        this.success = success;
        this.status = status;
        this.data = data;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    // 성공 응답 - 데이터가 있는 경우
    public static <T> AppResponse<T> success(AppStatusCode status, T data) {
        return new AppResponse<>(true, status, data, null);
    }

    // 성공 응답 - 데이터가 없는 경우 (예: 204 No Content)
    public static <T> AppResponse<T> success(AppStatusCode status) {
        return new AppResponse<>(true, status, null, null);
    }

    // 실패 응답
    public static <T> AppResponse<T> error(AppStatusCode status, AppError error) {
        return new AppResponse<>(false, status, null, error);
    }
}
