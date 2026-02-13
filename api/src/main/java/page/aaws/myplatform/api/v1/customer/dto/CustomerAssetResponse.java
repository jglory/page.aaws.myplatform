package page.aaws.myplatform.api.v1.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "수리 대상 건물(자산) 정보 응답")
public class CustomerAssetResponse {

    @Schema(description = "자산 고유키", example = "101")
    private Long id;

    @Schema(description = "건물명", example = "성수동 우리집")
    private String name;

    @Schema(description = "도로명 주소", example = "서울시 성동구 아차산로 123")
    private String address;

    @Schema(description = "지역", example = "서울 성동구")
    private String region;

    @Schema(description = "등록 일시")
    private LocalDateTime createdAt;
}