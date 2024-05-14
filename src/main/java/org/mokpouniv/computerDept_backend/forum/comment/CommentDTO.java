package org.mokpouniv.computerDept_backend.forum.comment;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("comment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    /**
     * randomUUID로 자동 생성됨.
     */
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String author;

    private String content;

    @Builder.Default
    private LocalDateTime posted_time = LocalDateTime.now();
}
