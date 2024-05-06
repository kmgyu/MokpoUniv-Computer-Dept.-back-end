package org.mokpouniv.computerDept_backend.forum;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notice")
@NoArgsConstructor
@Data
@Builder
public class ForumEntity {
    @Id
    private String id;  //식별하이디
    private String title; // 게시물 제목
    private String author; // 게시물 작성자
    private String content; // 게시물 내용

    public ForumEntity(String id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }
}