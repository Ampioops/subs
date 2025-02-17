package om.user_notifications.controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import om.user_notifications.model.param.ContactInfoParam;
import om.user_notifications.model.response.ContactInfoResponse;
import om.user_notifications.service.ContactInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user_notification/contact_information")
@RequiredArgsConstructor //Автоматом конструктор создает DI
@Tag(name = "Контактная информация", description = "Управление данными контактной информации")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    @GetMapping(value = "/{userId}")
    public ContactInfoResponse getContactInfoByUserId(@PathVariable UUID userId) {
        return contactInfoService.getContactInfoByUserId(userId);
    }

    @GetMapping(value = "/")
    public Page<ContactInfoResponse> getUsersContactInfo(
            @RequestBody(required = false) ContactInfoParam params,
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Parameter(description = "Пропуск указанного количества строк") Integer offset, //Пагинация
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) @Parameter(description = "Предел общего количества строк") Integer limit
    ){
        return contactInfoService.getUsersContactInfo(params, offset, limit);
    }
}
