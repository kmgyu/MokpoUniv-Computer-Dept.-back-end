package org.mokpouniv.computerDept_backend.forum.comment;

import lombok.*;
import org.springframework.data.annotation.Id;

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

    private String posted_time;
}
