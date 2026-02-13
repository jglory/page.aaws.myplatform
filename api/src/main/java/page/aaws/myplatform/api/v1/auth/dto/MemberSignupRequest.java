package page.aaws.myplatform.api.v1.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import page.aaws.myplatform.domain.member.model.MemberType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "일반 사용자 회원가입 요청")
public class MemberSignupRequest {
    @Email
    @NotBlank
    @Schema(description = "이메일(아이디)", example = "user@example.com")
    private String email;

    @NotNull
    @Schema(description = "회원 유형", example = "CUSTOMER")
    private MemberType type;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
    @Schema(description = "비밀번호 (8자 이상, 영문+숫자+특수문자 조합)", example = "Password123!")
    private String password;

    @NotBlank
    @Schema(description = "성함", example = "홍길동")
    private String name;

    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$")
    @Schema(description = "연락처", example = "01012345678")
    private String mobileNo;
}
