package page.aaws.myplatform.api.v1.platform;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import page.aaws.myplatform.api.v1.contract.dto.ContractResponse;
import page.aaws.myplatform.api.v1.contract.dto.ContractScheduleRequest;
import page.aaws.myplatform.domain.contract.model.ContractStatus;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "Platform", description = "중개플랫폼 관련 API")
@RestController
@RequestMapping("/api/v1/platform/{platformId}/contract")
@RequiredArgsConstructor
public class ContractController {
    @Operation(summary = "공사 일정 등록 및 공유", description = "관리자가 합의된 공사 시작/종료일을 등록합니다. 등록 시 채팅방에 일정 카드가 공유됩니다.")
    @PostMapping("/{contractId}/schedule")
    public ResponseEntity<AppResponse<ContractResponse>> registerSchedule(
            @Parameter(description = "플랫폼 고유키", example = "10") @PathVariable Long platformId,
            @Parameter(description = "계약 고유키", example = "10000")@PathVariable Long contractId,
            @Valid @RequestBody ContractScheduleRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                ContractResponse.builder()
                                        .id(contractId)
                                        .customerId(1L)
                                        .partnerId(500L)
                                        .estimationRequestId(1000L)
                                        .scheduleStartedAt(request.getScheduleStartedAt())
                                        .scheduleFinishedAt(request.getScheduleFinishedAt())
                                        .build()
                                )
                );
    }

    @Operation(summary = "계약 정보 열람", description = "관리자가 계약 정보를 조회합니다.")
    @GetMapping("/{contractId}")
    public ResponseEntity<AppResponse<ContractResponse>> getContract(
            @Parameter(description = "플랫폼 고유키", example = "10") @PathVariable Long platformId,
            @Parameter(description = "계약 고유키", example = "10000")@PathVariable Long contractId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                ContractResponse.builder()
                                        .id(contractId)
                                        .customerId(1L)
                                        .partnerId(500L)
                                        .estimationRequestId(1000L)
                                        .scheduleStartedAt(LocalDateTime.now())
                                        .scheduleFinishedAt(LocalDateTime.now())
                                        .build()
                        )
                );
    }

    @Operation(summary = "계약 상태 변경", description = "관리자가 계약 상태를 선택하여 업데이트 합니다.")
    @PutMapping("/{contractId}/status/{status}")
    public ResponseEntity<AppResponse<ContractResponse>> updateContractStatus(
            @Parameter(description = "플랫폼 고유키", example = "10") @PathVariable Long platformId,
            @Parameter(description = "계약 고유키", example = "10000")@PathVariable Long contractId,
            @Parameter(description = "계약 상태. 예정, 진행 중, 완료 등의 상태", example = "COMPLETED") @PathVariable ContractStatus status
            ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                ContractResponse.builder()
                                        .id(contractId)
                                        .customerId(1L)
                                        .partnerId(500L)
                                        .estimationRequestId(1000L)
                                        .scheduleStartedAt(LocalDateTime.now())
                                        .scheduleFinishedAt(LocalDateTime.now())
                                        .actualStartedAt(LocalDateTime.now())
                                        .actualFinishedAt(LocalDateTime.now())
                                        .confirmedAt(LocalDateTime.now())
                                        .completedAt(LocalDateTime.now())
                                        .build()
                        )
                );
    }
}
