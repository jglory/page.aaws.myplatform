package page.aaws.myplatform.api.v1.estimation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "견적(입찰) 제출 요청")
public class BidCreateRequest {
    @Positive
    @NotNull
    @Schema(description = "견적 금액", example = "3000000")
    private Integer price;

    @Positive @NotNull
    @Schema(description = "소요 기간 (일 단위)", example = "14")
    private Integer duration;

    @Size(max = 300)
    @Schema(description = "기타 사항 및 옵션", example = "하자보증보험 발급 가능, 폐기물 처리 포함")
    private String options;
}

