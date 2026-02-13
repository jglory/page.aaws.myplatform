package page.aaws.myplatform.api.v1.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import page.aaws.myplatform.api.v1.auth.dto.MemberSignupRequest;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Schema(description = "일반 사용자 회원가입 요청")
public class CustomerSignupRequest {
    @Valid
    @NotNull
    private MemberSignupRequest member;

    @AssertTrue(message = "이용약관에 동의해야 합니다.")
    @NotNull
    @Schema(description = "이용약관 동의 여부", example = "true")
    private Boolean isAgreedToTermsAndConditions;
}
