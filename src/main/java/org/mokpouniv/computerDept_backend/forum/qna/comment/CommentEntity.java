package org.mokpouniv.computerDept_backend.forum.qna.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    private String id;

    private String author;

    private String content;

    private LocalDateTime posted_time;

}
