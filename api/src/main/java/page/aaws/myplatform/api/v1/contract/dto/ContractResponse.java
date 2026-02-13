package page.aaws.myplatform.api.v1.contract.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "계약 및 공사 상세 정보 응답")
public class ContractResponse {
    @Schema(description = "계약 고유키", example = "101")
    private Long id;
    @Schema(description = "고객 고유키", example = "1")
    private Long customerId;
    @Schema(description = "파트너(업체) 고유키", example = "500")
    private Long partnerId;
    @Schema(description = "연관된 견적 의뢰 고유키", example = "1000")
    private Long estimationRequestId;

    @Schema(description = "공사 시작 예정일", example = "2024-01-18 14:30:00")
    private LocalDateTime scheduleStartedAt;
    @Schema(description = "공사 종료 예정일", example = "2024-01-19 14:30:00")
    private LocalDateTime scheduleFinishedAt;

    @Schema(description = "실제 공사 시작일시", example = "2024-01-18 14:30:00")
    private LocalDateTime actualStartedAt;
    @Schema(description = "실제 공사 종료일시", example = "2024-01-20 14:30:00")
    private LocalDateTime actualFinishedAt;

    @Schema(description = "수리 완료 확인 일시 (고객 동의 시점)", example = "2024-01-24 14:30:00")
    private LocalDateTime confirmedAt;
    @Schema(description = "계약/결제 완료 일시", example = "2024-02-01 14:30:00")
    private LocalDateTime completedAt;
}
