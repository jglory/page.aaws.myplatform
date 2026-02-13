package page.aaws.myplatform.api.v1.partner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import page.aaws.myplatform.api.v1.partner.dto.PartnerSignupRequest;
import page.aaws.myplatform.global.exception.AppStatus;
import page.aaws.myplatform.global.model.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Partner", description = "파트너 관련 API")
@RestController
@RequestMapping("/api/v1/partner")
@RequiredArgsConstructor
public class PartnerController {
    private final ObjectMapper objectMapper;

    @Operation(summary = "업체 사용자 가입 신청", description = "사업자 정보와 함께 가입 신청을 제출합니다. 관리자 승인이 필요합니다.")
    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AppResponse<String>> signupPartner(
            @Parameter(
                    description = "가입 신청 정보 (JSON)",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = PartnerSignupRequest.class),
                            examples = @ExampleObject(
                                    value = "{\n" +
                                            "  \"member\": {\n" +
                                            "    \"email\": \"partner@example.com\",\n" +
                                            "    \"password\": \"Password123!\",\n" +
                                            "    \"name\": \"홍길동\",\n" +
                                            "    \"mobileNo\": \"010-1234-5678\",\n" +
                                            "    \"type\": \"PARTNER\"\n" +
                                            "  },\n" +
                                            "  \"companyRegistrationNo\": \"123-45-67890\",\n" +
                                            "  \"companyName\": \"(주)리페어러스\",\n" +
                                            "  \"ceoName\": \"이업체\",\n" +
                                            "  \"companyAddress\": \"서울시 강남구 테헤란로 123\"\n" +
                                            "}"
                            )
                    )
            )
            @RequestParam
            String request,
            @Parameter(description = "사업자 등록증 이미지 파일")
            @RequestParam MultipartFile companyRegistrationFile
    ) throws JsonProcessingException {
        PartnerSignupRequest signupRequest = this.objectMapper.readValue(request, PartnerSignupRequest.class);
        return ResponseEntity.status(HttpStatus.OK).body(AppResponse.success(AppStatus.OK, "회원가입이 처리 되었습니다."));
    }
}
