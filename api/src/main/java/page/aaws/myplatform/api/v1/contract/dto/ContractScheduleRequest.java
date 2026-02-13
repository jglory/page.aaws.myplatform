package page.aaws.myplatform.api.v1.contract.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "공사 일정 등록 요청")
public class ContractScheduleRequest {
    @NotNull
    @Schema(description = "공사 시작 예정일", example = "2026-02-01T09:00:00")
    private LocalDateTime scheduleStartedAt;

    @NotNull
    @Schema(description = "공사 종료 예정일", example = "2026-02-15T18:00:00")
    private LocalDateTime scheduleFinishedAt;
}
