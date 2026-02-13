package page.aaws.myplatform.api.v1.estimation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Schema(description = "수리 의뢰 등록 요청")
public class EstimationCreateRequest {
    @NotNull
    @Schema(description = "고객 소유 건물 고유키", example = "101")
    private Long customerAssetId;

    @NotBlank @Size(max = 100)
    @Schema(description = "의뢰 제목", example = "이사로 인한 사무실 원상복구")
    private String title;

    @NotBlank @Size(max = 500)
    @Schema(description = "의뢰 항목들 (콤마로 구분)", example = "전기,배관,목공")
    private String subjects;

    @NotBlank
    @Schema(description = "의뢰 상세 내용", example = "45평 사무실이고 빠른 시일 내에 작업이 필요합니다.")
    private String description;
}