package om.subs.client;

import lombok.RequiredArgsConstructor;
import org.common.common_utils.response.BookResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatusCode;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class LibraryClient {

    private final WebClient webClient;

    public Mono<BookResponseDTO> getBookById(Integer bookId) {
        return webClient.get()
                .uri("/book/{bookId}", bookId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new RuntimeException("Книга с ID " + bookId + " не найдена")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new RuntimeException("Ошибка на стороне библиотеки")))
                .bodyToMono(BookResponseDTO.class)
                .timeout(Duration.ofSeconds(3)) // Таймаут на случай зависших запросов
                .retry(2);
    }
}
