package om.subs.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import om.subs.model.param.SubscriptionParam;
import om.subs.model.request.SubscriptionRequest;
import om.subs.model.response.SubscriptionResponse;
import om.subs.service.SubscriptionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subs/subscription")
@RequiredArgsConstructor //Автоматом конструктор создает DI
@Tag(name = "Подписки", description = "Управление данными подписки")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/")
    public SubscriptionResponse createSubscription(@RequestBody SubscriptionRequest request) {
        return subscriptionService.createSubscription(request);
    }

    @PatchMapping("/{userId}")
    public SubscriptionResponse updateSubscription(@PathVariable Integer userId, @RequestBody SubscriptionRequest request) {
        return subscriptionService.updateSubscriptionInfo(userId, request);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSubscription(@PathVariable Integer id) {
        subscriptionService.deleteSubscriptionById(id);
    }

    @GetMapping(value = "/")
    public Page<SubscriptionResponse> getSubscriptions(
            @RequestBody(required = false) SubscriptionParam params,
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Parameter(description = "Пропуск указанного количества строк") Integer offset, //Пагинация
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) @Parameter(description = "Предел общего количества строк") Integer limit
    ){
        return subscriptionService.getSubscriptions(params, offset, limit);
    }

    @GetMapping(value = "/{id}")
    public SubscriptionResponse getSubscriptionById(@PathVariable Integer id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @GetMapping(value = "/user/{userId}")
    public Page<SubscriptionResponse> getSubscriptionsByUser(
            @PathVariable Integer userId,
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) @Parameter(description = "Пропуск указанного количества строк") Integer offset, //Пагинация
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) @Parameter(description = "Предел общего количества строк") Integer limit
    ){
        return subscriptionService.getSubscriptionsByUser(userId, offset, limit);
    }
}
