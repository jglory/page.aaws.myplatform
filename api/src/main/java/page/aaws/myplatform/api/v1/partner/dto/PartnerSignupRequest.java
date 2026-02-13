package page.aaws.myplatform.api.v1.partner.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import page.aaws.myplatform.api.v1.auth.dto.MemberSignupRequest;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Schema(description = "업체 사용자 가입 신청 요청")
public class PartnerSignupRequest {
    @Valid
    @NotNull
    private MemberSignupRequest member;

    @NotBlank
    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$")
    @Schema(description = "사업자 등록번호", example = "123-45-67890")
    private String companyRegistrationNo;

    @NotBlank
    @Schema(description = "상호명", example = "(주)리페어러스")
    private String companyName;

    @NotBlank
    @Schema(description = "대표자명", example = "이업체")
    private String ceoName;

    @NotBlank
    @Schema(description = "사업장 주소", example = "서울시 강남구 테헤란로 123")
    private String companyAddress;
}