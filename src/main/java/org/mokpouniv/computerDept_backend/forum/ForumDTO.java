package org.mokpouniv.computerDept_backend.forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
프론트 - 백 데이터 전송 클래스
 */
@Getter
@Setter
@AllArgsConstructor
public class ForumDTO {
    private String Title;
    private String content;
// 게시자, Original Poster
    private String writer;

//    toForumEntity method 만들어둘 것.
}
