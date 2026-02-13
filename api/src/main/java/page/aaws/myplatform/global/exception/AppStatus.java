package page.aaws.myplatform.global.exception;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppStatus implements AppStatusCode {
    OK(1, "성공했습니다."),
    FAILED(1000, "처리에 실패했습니다."),
    NOT_AVAILABLE(1001, "유효하지 않습니다."),
    INVALID_REQUEST(1002, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(1003, "서버 내부 오류가 발생했습니다."),
    UNAUTHORIZED(1004, "인증에 실패했습니다."),
    FORBIDDEN(1005, "접근 권한이 없습니다.");

    private final int value;
    private final String message;

    @Override
    @JsonValue
    public int value() {
        return this.value;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public boolean isError() {
        return this.value>=1000;
    }
}
