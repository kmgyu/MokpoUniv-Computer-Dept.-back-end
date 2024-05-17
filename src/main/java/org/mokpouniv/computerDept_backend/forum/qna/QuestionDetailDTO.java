package org.mokpouniv.computerDept_backend.forum.qna;

import lombok.*;
import org.mokpouniv.computerDept_backend.forum.comment.CommentDTO;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class QuestionDetailDTO {
    private String id;  //식별아이디

    private String title; // 게시물 제목

    private String author; // 게시물 작성자

    private String content; // 게시물 내용

    private int view; // 조회수

    private LocalDateTime posted_time;

    private List<CommentDTO> commentDTOList;
}
