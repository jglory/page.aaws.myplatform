package page.aaws.myplatform.api.v1.certificate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "인증/면허 정보 마스터 등록 요청")
public class CertificateCreateRequest {
    @NotBlank
    @Size(max = 100)
    @Schema(description = "인증/면허 명칭", example = "건설업 면허")
    private String name;
}
