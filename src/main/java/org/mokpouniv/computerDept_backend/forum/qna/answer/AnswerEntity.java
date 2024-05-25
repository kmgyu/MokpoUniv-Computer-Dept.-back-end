package org.mokpouniv.computerDept_backend.forum.qna.answer;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.qna.comment.CommentEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerEntity {
    private String id;

    private String author;

    private String content;

    private LocalDateTime posted_time;

    private List<CommentEntity> commentEntityList;
}
