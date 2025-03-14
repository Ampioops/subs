package om.subs.model.event;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubEvent {
    private Integer userId;
    private String bookTitle;
    private String genre;
    private Integer authorId;
}
