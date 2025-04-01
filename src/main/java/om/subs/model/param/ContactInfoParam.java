package om.subs.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Параметры контактной информации пользователя")
public class ContactInfoParam {

    @Schema(description = "Содержит id пользователя")
    private Integer userIdCont;

    @Schema(description = "Почта содержит строку")
    private String emailCont;

    @Schema(description = "Телефон содержит")
    private String numberPhoneCont;
}
