package page.aaws.myplatform.api.v1.certificate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "인증 정보 마스터 응답")
public class CertificateResponse {
    @Schema(description = "인증 고유키", example = "1")
    private Long id;
    @Schema(description = "인증/면허 명칭", example = "건설업 면허")
    private String name;
    @Schema(description = "등록 일시", example = "2026-01-19 00:13:04")
    private LocalDateTime createdAt;
}
