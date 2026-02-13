package page.aaws.myplatform.api.v1.certificate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import page.aaws.myplatform.api.v1.certificate.dto.CertificateCreateRequest;
import page.aaws.myplatform.api.v1.certificate.dto.CertificateResponse;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Certificate", description = "인증/면허 마스터 정보 관련 API")
@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateController {
    @Operation(summary = "인증/면허 마스터 정보 등록", description = "플랫폼에 새로운 인증/면허 종류를 등록합니다.")
    @PostMapping
    public ResponseEntity<AppResponse<CertificateResponse>> createCertificate(@Valid @RequestBody CertificateCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                CertificateResponse.builder()
                                        .id(1L)
                                        .name(request.getName())
                                        .createdAt(LocalDateTime.now())
                                        .build()
                                )
                );
    }

    @Operation(summary = "인증/면허 마스터 정보 목록", description = "인증/면허 마스터 정보 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<AppResponse<List<CertificateResponse>>> getCertificates() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AppResponse.success(
                                AppStatus.OK,
                                List.of(
                                        CertificateResponse.builder()
                                                .id(1L)
                                                .name("건설업 면허")
                                                .createdAt(LocalDateTime.now())
                                                .build(),
                                        CertificateResponse.builder()
                                                .id(2L)
                                                .name("전기 설비/설치")
                                                .createdAt(LocalDateTime.now())
                                                .build(),
                                        CertificateResponse.builder()
                                                .id(3L)
                                                .name("누수탐지")
                                                .createdAt(LocalDateTime.now())
                                                .build()
                                )
                        )
                );
    }
}
