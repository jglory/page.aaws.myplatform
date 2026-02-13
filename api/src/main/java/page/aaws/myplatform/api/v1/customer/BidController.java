package page.aaws.myplatform.api.v1.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import page.aaws.myplatform.api.v1.estimation.dto.BidResponse;
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

@Tag(name = "Customer", description = "고객 관련 API")
@RestController("CustomerBidController")
@RequestMapping("/api/v1/customer/{customerId}/estimation-request/{estimationRequestId}/bid")
@RequiredArgsConstructor
public class BidController {
    @Operation(summary = "제출된 견적 목록 조회 및 비교", description = "고객이 자신의 의뢰에 제출된 모든 견적을 나란히 비교합니다.")
    @GetMapping
    public ResponseEntity<AppResponse<List<BidResponse>>> getBidsForEstimationRequest(
            @Parameter(description = "고객 고유키", example = "1") @PathVariable Long customerId,
            @Parameter(description = "견적 의뢰 고유키", example = "100") @PathVariable Long estimationRequestId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                List.of(
                                        BidResponse.builder()
                                                .id(1000L)
                                                .estimationRequestId(estimationRequestId)
                                                .partnerId(50L)
                                                .price(1500000)
                                                .duration(15)
                                                .options("방수 처리 포함, 폐기물 처리 별도")
                                                .createdAt(LocalDateTime.now())
                                                .build(),
                                        BidResponse.builder()
                                                .id(1001L)
                                                .estimationRequestId(estimationRequestId)
                                                .partnerId(51L)
                                                .price(1800000)
                                                .duration(15)
                                                .options("방수 처리 포함, 폐기물 처리 별도")
                                                .createdAt(LocalDateTime.now())
                                                .build()
                                        )));
    }

    @Operation(summary = "제출된 견적 목록 조회", description = "고객이 자신의 의뢰에 제출된 견적의 정보를 조회합니다.")
    @GetMapping("/{bidId}")
    public ResponseEntity<AppResponse<BidResponse>> getBidForEstimationRequest(
            @Parameter(description = "고객 고유키", example = "1") @PathVariable Long customerId,
            @Parameter(description = "견적 의뢰 고유키", example = "100") @PathVariable Long estimationRequestId,
            @Parameter(description = "견적 정보 고유키", example = "1000") @PathVariable Long bidId

    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                BidResponse.builder()
                                        .id(bidId)
                                        .estimationRequestId(estimationRequestId)
                                        .partnerId(50L)
                                        .price(1500000)
                                        .duration(15)
                                        .options("방수 처리 포함, 폐기물 처리 별도")
                                        .createdAt(LocalDateTime.now())
                                        .build()
                        )
                );
    }
}
