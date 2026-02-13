package page.aaws.myplatform.global.exception;

import page.aaws.myplatform.global.model.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AppException.class, BindException.class})
    public ResponseEntity<AppResponse<Void>> handleAppException(Exception e) {
        if (e instanceof AppException appException) {
            return ResponseEntity.status(appException.getStatusCode()).body(AppResponse.error(appException.getAppStatusCode(), AppError.of(appException.getAppStatusCode(), e.getMessage())));
        } else if (e instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AppResponse.error(AppStatus.FAILED, AppError.of(AppStatus.FAILED, errors.toString())));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(AppResponse.error(AppStatus.FAILED, AppError.of(AppStatus.FAILED, e.getMessage())));
    }
}