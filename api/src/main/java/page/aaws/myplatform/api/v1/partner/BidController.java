package page.aaws.myplatform.api.v1.partner;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import page.aaws.myplatform.api.v1.estimation.dto.BidCreateRequest;
import page.aaws.myplatform.api.v1.estimation.dto.BidResponse;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Partner", description = "파트너 관련 API")
@RestController("PartnerBidController")
@RequestMapping("/api/v1/partner/{partnerId}")
@RequiredArgsConstructor
public class BidController {
    @Operation(summary = "견적 제출", description = "파트너가 특정 의뢰에 대해 금액, 기간, 옵션을 포함한 견적을 제출합니다.")
    @PostMapping("/estimation-request/{estimationRequestId}/bid")
    public ResponseEntity<AppResponse<BidResponse>> createBid(
            @Parameter(description = "파트너 고유키", example = "50") @PathVariable Long partnerId,
            @Parameter(description = "견적 요청 정보 고유키", example = "201") @PathVariable Long estimationRequestId,
            @Valid @RequestBody BidCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                BidResponse.builder()
                                        .id(1000L)
                                        .estimationRequestId(estimationRequestId)
                                        .partnerId(partnerId)
                                        .price(1500000)
                                        .duration(15)
                                        .options("방수 처리 포함, 폐기물 처리 별도")
                                        .createdAt(LocalDateTime.now())
                                        .build()

                        ));
    }
}
