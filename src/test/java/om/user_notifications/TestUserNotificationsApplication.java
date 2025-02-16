package om.user_notifications;

import org.springframework.boot.SpringApplication;

public class TestUserNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.from(UserNotificationsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
