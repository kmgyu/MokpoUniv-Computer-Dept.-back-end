package org.mokpouniv.computerDept_backend.forum.qna.question;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.qna.answer.AnswerEntity;
import org.mokpouniv.computerDept_backend.forum.comment.CommentEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("question")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEntity {
    @Id
    private String id;  //식별아이디

    private String title; // 게시물 제목

    private String author; // 게시물 작성자

    private String content; // 게시물 내용

    private int view; // 조회수

    private LocalDateTime posted_time;

    private boolean isAnswer;

    private List<AnswerEntity> answerEntityList;

    private List<CommentEntity> commentEntityList;

}
