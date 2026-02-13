package page.aaws.myplatform.api.v1.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import page.aaws.myplatform.api.v1.estimation.dto.EstimationCreateRequest;
import page.aaws.myplatform.api.v1.estimation.dto.EstimationResponse;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Customer", description = "고객 관련 API")
@RestController("CustomerEstimationRequestController")
@RequestMapping("/api/v1/customer/{customerId}/estimation-request")
@RequiredArgsConstructor
public class EstimationRequestController {
    private final ObjectMapper objectMapper;

    @Operation(summary = "수리 의뢰 등록", description = "사진과 내용을 포함하여 수리 의뢰를 생성합니다. AI 분석을 통해 제목/내용이 보완될 수 있습니다.")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<AppResponse<EstimationResponse>> createEstimation(
            @Parameter(description = "고객 고유키", example = "1")
            @PathVariable Long customerId,
            @Parameter(
                    description = "수리 의뢰 정보 (JSON)",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = EstimationCreateRequest.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"customerAssetId\": 201,\n" +
                                            "  \"title\": \"이사로 인한 사무실 원상복구\",\n" +
                                            "  \"subjects\": \"전기,배관,목공\",\n" +
                                            "  \"description\": \"45평 사무실이고 빠른 시일 내에 작업이 필요합니다.\"\n" +
                                            "}"
                            )
                    )
            )
            @RequestParam String request,
            @Parameter(description = "수리 의뢰 사진들")
            @RequestParam(required = false) List<MultipartFile> images) throws JsonProcessingException {
        EstimationCreateRequest estimationCreateRequest = this.objectMapper.readValue(request, EstimationCreateRequest.class);
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

    @Operation(summary = "의뢰 내역 조회", description = "로그인한 고객이 본인이 등록한 의뢰 목록과 진행 상태를 확인합니다.")
    @GetMapping
    public ResponseEntity<AppResponse<List<EstimationResponse>>> getEstimations(@Parameter(description = "고객 고유키", example = "1") @PathVariable Long customerId) {
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

    @Operation(summary = "의뢰 상세 내용 조회", description = "특정 의뢰의 상세 내용과 사진, 제출된 견적 수 등을 확인합니다.")
    @GetMapping("/{estimationRequestId}")
    public ResponseEntity<AppResponse<EstimationResponse>> getEstimation(@Parameter(description = "견적 의뢰 고유키", example = "1") @PathVariable Long estimationRequestId) {
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
