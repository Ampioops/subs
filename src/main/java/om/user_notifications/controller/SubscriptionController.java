package om.user_notifications.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_notification/subscription")
@RequiredArgsConstructor //Автоматом конструктор создает DI
@Tag(name = "Подписки", description = "Управление данными подписки")
public class SubscriptionController {
}
