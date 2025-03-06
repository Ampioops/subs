package om.subs.model.event;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEvent {
    private String eventType; // Тип события: "CREATED" или "DELETED"
    private Integer bookId;      // ID книги
    private Integer genreId;    // ID жанра
    private Integer authorId;   // ID автора
}
