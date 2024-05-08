package org.mokpouniv.computerDept_backend.forum;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumDTO {
    private String id;
    private String title;
    private String author;
    private String content;

    public ForumEntity toForumEntity() {
        return new ForumEntity(id, title, author, content);
    }
}