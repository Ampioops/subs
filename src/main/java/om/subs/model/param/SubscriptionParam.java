package om.subs.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Schema(description = "Параметры автора")
public class SubscriptionParam {

    @Schema(description = "Содержит id пользователя")
    private UUID userIdCont;

    @Schema(description = "Содержит id объекта подписки")
    private UUID referenceIdCont;

    @Schema(description = "Создано после определенного времени")
    private LocalDateTime createdFrom;

    @Schema(description = "Создано до определенного времени")
    private LocalDateTime createdBefore;
}
