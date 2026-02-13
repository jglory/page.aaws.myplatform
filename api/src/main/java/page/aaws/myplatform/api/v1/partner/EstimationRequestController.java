package page.aaws.myplatform.api.v1.partner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import page.aaws.myplatform.api.v1.estimation.dto.EstimationResponse;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Partner", description = "파트너 관련 API")
@RestController("PartnerEstimationRequestController")
@RequestMapping("/api/v1/partner/{partnerId}/estimation-request")
@RequiredArgsConstructor
public class EstimationRequestController {
    @Operation(summary = "입찰 가능 의뢰 목록 조회", description = "업체 사용자가 수리 종류 및 지역 필터를 사용하여 입찰 가능한 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<AppResponse<List<EstimationResponse>>> getEstimations(@Parameter(description = "파트너 고유키", example = "1") @PathVariable Long partnerId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                List.of(
                                        EstimationResponse.builder()
                                                .id(101L)
                                                .customerId(1L)
                                                .customerAssetId(1000L)
                                                .title("화장실 타일 교체 요청")
                                                .subjects("타일, 방수")
                                                .description("화장실 바닥 타일이 깨져서 교체하고 싶습니다.")
                                                .imageIds(List.of(13L, 14L, 15L, 16L))
                                                .bidId(201L) // 낙찰된 경우
                                                .bitMatchedAt(LocalDateTime.of(2026, 1, 23, 10, 0, 0))
                                                .createdAt(LocalDateTime.of(2026, 1, 23, 10, 0, 0))
                                                .build(),
                                        EstimationResponse.builder()
                                                .id(102L)
                                                .customerId(1L)
                                                .customerAssetId(1001L)
                                                .title("화장실 타일 교체 요청")
                                                .subjects("타일, 방수")
                                                .description("화장실 바닥 타일이 깨져서 교체하고 싶습니다.")
                                                .imageIds(List.of(23L, 24L, 25L, 26L))
                                                .bidId(202L) // 낙찰된 경우
                                                .bitMatchedAt(LocalDateTime.of(2026, 1, 24, 10, 0, 0))
                                                .createdAt(LocalDateTime.of(2026, 1, 24, 10, 0, 0))
                                                .build()
                                )
                        )
                );
    }
}
