package page.aaws.myplatform.api.v1.estimation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "의뢰 정보 응답")
public class EstimationResponse {
    @Schema(description = "의뢰 고유키", example = "101")
    private Long id;

    @Schema(description = "고객 고유키", example = "1")
    private Long customerId;

    @Schema(description = "고객 자산 고유키", example = "1000")
    private Long customerAssetId;

    @Schema(description = "의뢰 제목", example = "화장실 타일 교체 요청")
    private String title;

    @Schema(description = "수리 항목", example = "타일, 방수")
    private String subjects;

    @Schema(description = "상세 설명", example = "화장실 바닥 타일이 깨져서 교체하고 싶습니다.")
    private String description;

    @Schema(description = "낙찰된 견적 고유키", example = "201")
    private Long bidId;

    @Schema(description = "건젹 선택일시", example = "2026-01-23 10:00:00")
    private LocalDateTime bitMatchedAt;

    @Schema(description = "첨부 이미지 고유키 목록", example = "[13, 14, 15, 16]")
    private List<Long> imageIds;

    @Schema(description = "생성 일시", example = "2026-01-18 10:00:00")
    private LocalDateTime createdAt;
}


