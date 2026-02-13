package page.aaws.myplatform.global.exception;

import lombok.Getter;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

/**
 * App의 api 처리 예외 기본 클래스
 *
 * <p>모든 api 처리 예외는 이 클래스를 상속받아 구현합니다.
 */
@Getter
public class AppException extends ResponseStatusException {
    private final AppStatusCode appStatus;

    public AppException(HttpStatusCode status, AppStatusCode appStatus) {
        super(status);
        this.appStatus = appStatus;
    }

    public AppException(HttpStatusCode status, AppStatusCode appStatus, @Nullable String reason) {
        super(status, reason);
        this.appStatus = appStatus;
    }

    public AppException(int rawStatusCode, AppStatusCode appStatus, @Nullable String reason, @Nullable Throwable cause) {
        super(rawStatusCode, reason, cause);
        this.appStatus = appStatus;
    }

    public AppException(HttpStatusCode status, AppStatusCode appStatus, @Nullable String reason, @Nullable Throwable cause) {
        super(status, reason, cause);
        this.appStatus = appStatus;
    }

    public AppException(HttpStatusCode status, AppStatusCode appStatus, @Nullable String reason, @Nullable Throwable cause, @Nullable String messageDetailCode, @Nullable Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
        this.appStatus = appStatus;
    }

    public AppStatusCode getAppStatusCode() {
        return this.appStatus;
    }

    @Override
    @NonNull
    public String getMessage() {
        return (this.getReason() != null ? this.getReason() : this.appStatus.message());
    }
}
