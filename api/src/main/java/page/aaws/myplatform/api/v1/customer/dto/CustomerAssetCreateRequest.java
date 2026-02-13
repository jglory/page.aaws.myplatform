package page.aaws.myplatform.api.v1.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "수리 대상 건물(자산) 등록 및 수정 요청")
public class CustomerAssetCreateRequest {

    @NotBlank
    @Size(max = 30)
    @Schema(description = "건물명 (예: 우리 집, 성수동 매장)", example = "성수동 우리집")
    private String name;

    @NotBlank
    @Size(max = 150)
    @Schema(description = "도로명 주소", example = "서울시 성동구 아차산로 123")
    private String address;

    @NotBlank
    @Size(max = 20)
    @Schema(description = "지역 (시/군/구 단위)", example = "서울 성동구")
    private String region;
}