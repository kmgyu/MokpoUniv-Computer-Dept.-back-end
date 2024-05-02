package org.mokpouniv.computerDept_backend.forum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("Notice")
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class ForumEntity {
    @Id
    private String id;  //식별아이디
    private String title; // 게시물 제목
    private String author; // 게시물 작성자
    private String content; // 게시물 내용

//    public ForumEntity(String title, String author, String content) {
//        this.id = UUID.randomUUID().toString(); // UUID를 사용하여 고유한 아이디 생성
//        this.title = title;
//        this.author = author;
//        this.content = content;
//    }

    public ForumDTO toForumDTO() {
        return new ForumDTO(id,title, author, content);
    }
}
