package org.mokpouniv.computerDept_backend.forum.qna.question;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.qna.answer.AnswerDTO;
import org.mokpouniv.computerDept_backend.forum.comment.CommentDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class QuestionDetailDTO {

    @Builder.Default
    private String id = UUID.randomUUID().toString();  //식별아이디

    private String title; // 게시물 제목

    private String author; // 게시물 작성자

    private String content; // 게시물 내용

    @Builder.Default
    private int view = 0; // 조회수

    @Builder.Default
    private LocalDateTime posted_time = LocalDateTime.now();

    @Builder.Default
    private boolean isAnswer = false;

    @Builder.Default
    private List<AnswerDTO> answerDTOList = new ArrayList<>();

    @Builder.Default
    private List<CommentDTO> commentDTOList = new ArrayList<>();
}
