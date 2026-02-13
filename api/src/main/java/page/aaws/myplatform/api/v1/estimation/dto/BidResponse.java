package page.aaws.myplatform.api.v1.estimation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "견적 정보 응답")
public class BidResponse {
    @Schema(description = "견적 정보 고유키", example = "1000")
    private Long id;

    @Schema(description = "견적 요청 정보 고유키", example = "201")
    private Long estimationRequestId;

    @Schema(description = "파트너 고유키", example = "50")
    private Long partnerId;

    @Schema(description = "견적 금액 (원)", example = "1500000")
    private Integer price;

    @Schema(description = "예상 소요 기간 (일)", example = "15")
    private Integer duration;

    @Schema(description = "추가 옵션 및 비고", example = "방수 처리 포함, 폐기물 처리 별도")
    private String options;

    @Schema(description = "견적 생성 일시", example = "2024-01-18 14:30:00")
    private LocalDateTime createdAt;
}
