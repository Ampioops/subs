package om.subs.controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import om.subs.model.param.ContactInfoParam;
import om.subs.model.request.ContactInfoRequest;
import om.subs.model.response.ContactInfoResponse;
import om.subs.service.ContactInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/subs/contact_information")
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

    @DeleteMapping("/{userId}")
    public void deleteContactInfoByUserId(@PathVariable UUID userId) {
        contactInfoService.deleteContactInfoById(userId);
    }

    @PatchMapping(value = "{userId}")
    public ContactInfoResponse updateUserContactInfo(@PathVariable UUID userId, @RequestBody ContactInfoRequest request){
        return contactInfoService.updateUserContactInfo(userId, request);
    }

    @PostMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ContactInfoResponse createUserContactInfo(@RequestBody ContactInfoRequest request, @PathVariable UUID userId) {
        return contactInfoService.createContactInfo(request);
    }
}
