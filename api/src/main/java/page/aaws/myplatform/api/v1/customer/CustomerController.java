package page.aaws.myplatform.api.v1.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import page.aaws.myplatform.api.v1.customer.dto.CustomerAssetCreateRequest;
import page.aaws.myplatform.api.v1.customer.dto.CustomerAssetResponse;
import page.aaws.myplatform.api.v1.customer.dto.CustomerSignupRequest;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Customer", description = "고객 관련 API")
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ObjectMapper objectMapper;

    @Operation(summary = "일반 사용자 회원가입", description = "이메일과 기본 정보를 입력하여 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<AppResponse<String>> signup(@Valid @RequestBody CustomerSignupRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(AppResponse.success(AppStatus.OK, "회원가입이 처리 되었습니다.")); // 중복되지 않음)
    }

    @Operation(summary = "새로운 수리 대상물 등록", description = "수리 의뢰의 대상이 될 건물 정보를 등록합니다.")
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/{customerId}/asset")
    public ResponseEntity<AppResponse<CustomerAssetResponse>> createAsset(
        @Parameter(description = "고객 고유키", example = "1") @PathVariable Long customerId,
        @Valid @RequestBody CustomerAssetCreateRequest request
    ) {
        CustomerAssetResponse mock = new CustomerAssetResponse();
        mock.setId(101L);
        mock.setName(request.getName());
        mock.setAddress(request.getAddress());
        mock.setRegion(request.getRegion());
        mock.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(AppResponse.success(AppStatus.OK, mock));
    }

    @Operation(summary = "본인의 수리 대상물 목록 조회", description = "현재 로그인된 사용자가 등록한 모든 건물 목록을 조회합니다.")
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{customerId}/asset")
    public ResponseEntity<AppResponse<List<CustomerAssetResponse>>> getMyAssets(
        @Parameter(description = "고객 고유키", example = "1") @PathVariable Long customerId
    ) {
        CustomerAssetResponse asset1 = new CustomerAssetResponse();
        asset1.setId(101L);
        asset1.setName("성수동 우리집");
        asset1.setAddress("서울시 성동구 아차산로 123");
        asset1.setRegion("서울 성동구");
        asset1.setCreatedAt(LocalDateTime.now().minusDays(10));

        CustomerAssetResponse asset2 = new CustomerAssetResponse();
        asset2.setId(102L);
        asset2.setName("강남 리페어러스 빌딩");
        asset2.setAddress("서울시 강남구 테헤란로 456");
        asset2.setRegion("서울 강남구");
        asset2.setCreatedAt(LocalDateTime.now().minusDays(5));

        return ResponseEntity.status(HttpStatus.OK).body(AppResponse.success(AppStatus.OK, List.of(asset1, asset2)));
    }
}
