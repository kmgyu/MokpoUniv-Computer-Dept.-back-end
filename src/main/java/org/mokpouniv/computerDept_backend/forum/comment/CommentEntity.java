package org.mokpouniv.computerDept_backend.forum.comment;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.qna.QuestionEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("comment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    private String id;

    private String author;

    private String content;

    private LocalDateTime posted_time;

    @DBRef
    QuestionEntity questionEntity;
}
