package org.mokpouniv.computerDept_backend.forum;

import lombok.*;

/*
프론트 - 백 데이터 전송 클래스
 */
@Data
@Builder
@AllArgsConstructor
public class ForumDTO {
    private String Title;
    private String content;
// 게시자, Original Poster
    private String author;

//    toForumEntity method 만들어둘 것.
    public ForumEntity toForumEntity() {
        return new ForumEntity(Title, content, author);
    }
}
