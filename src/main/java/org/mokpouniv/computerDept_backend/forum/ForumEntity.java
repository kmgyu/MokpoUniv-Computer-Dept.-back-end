package org.mokpouniv.computerDept_backend.forum;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notice")
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class ForumEntity {
//    @Id
    private String title; // 게시물 제목
    private String author; // 게시물 작성자
    private String content; // 게시물 내용

    public ForumDTO toForumDTO() {
        return new ForumDTO(title, author, content);
    }
}
