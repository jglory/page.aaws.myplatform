package page.aaws.myplatform.api.v1.platform;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import page.aaws.myplatform.api.v1.estimation.dto.EstimationResponse;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Platform", description = "중개플랫폼 관련 API")
@RestController("PlatformEstimationRequestController")
@RequestMapping("/api/v1/platform/{platformId}/estimation-request")
@RequiredArgsConstructor
public class EstimationRequestController {
    @Operation(summary = "의뢰 내역 조회", description = "관리자가 모든 의뢰 목록과 현재 상태를 조회합니다.")
    @GetMapping
    public ResponseEntity<AppResponse<List<EstimationResponse>>> getEstimations(@Parameter(description = "플랫폼 고유키", example = "1") @PathVariable Long platformId) {
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

    @Operation(summary = "의뢰 상세 내용 조회", description = "관리자가 특정 의뢰의 상세 내용과 사진, 제출된 견적 수 등을 확인합니다.")
    @GetMapping("/{estimationRequestId}")
    public ResponseEntity<AppResponse<EstimationResponse>> getEstimation(
            @Parameter(description = "플랫폼 고유키", example = "1") @PathVariable Long platformId,
            @Parameter(description = "견적 의뢰 고유키", example = "1") @PathVariable Long estimationRequestId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
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
                                        .createdAt(LocalDateTime.now())
                                        .build()
                        )
                );
    }
}
