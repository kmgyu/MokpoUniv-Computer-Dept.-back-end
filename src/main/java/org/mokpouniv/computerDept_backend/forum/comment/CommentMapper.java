package org.mokpouniv.computerDept_backend.forum.comment;

import org.mokpouniv.computerDept_backend.forum.qna.QuestionEntity;

public class CommentMapper {
    public static CommentEntity toEntity(CommentDTO commentDTO, QuestionEntity questionEntity) {
        return CommentEntity.builder()
                .id(commentDTO.getId())
                .author(commentDTO.getAuthor())
                .content(commentDTO.getContent())
                .posted_time(commentDTO.getPosted_time())
                .questionEntity(questionEntity)
                .build();
    }

    public static CommentDTO toDTO(CommentEntity commentEntity) {
        return CommentDTO.builder()
                .id(commentEntity.getId())
                .author(commentEntity.getAuthor())
                .content(commentEntity.getContent())
                .posted_time(commentEntity.getPosted_time())
                .question_id(commentEntity.questionEntity.getId())
                .build();
    }

}
