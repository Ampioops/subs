package om.user_notifications.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Параметры автора")
public class ContactInfoParam {

    @Schema(description = "Почта содержит строку")
    private String emailCont;
}
