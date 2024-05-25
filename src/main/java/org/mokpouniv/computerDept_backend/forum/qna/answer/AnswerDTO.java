package org.mokpouniv.computerDept_backend.forum.qna.answer;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.qna.comment.CommentDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String author;

    private String content;

    @Builder.Default
    private LocalDateTime posted_time = LocalDateTime.now();

//    채택된 데이터도 필요할 듯...? 그런게 없다.
//    private boolean selected;

    @Builder.Default
    private List<CommentDTO> commentDTOList = new ArrayList<>();
}
