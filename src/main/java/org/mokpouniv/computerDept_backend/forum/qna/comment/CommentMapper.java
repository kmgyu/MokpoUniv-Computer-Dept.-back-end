package org.mokpouniv.computerDept_backend.forum.qna.comment;

public class CommentMapper {
    public static CommentEntity toEntity(CommentDTO commentDTO) {
        return CommentEntity.builder()
                .id(commentDTO.getId())
                .author(commentDTO.getAuthor())
                .content(commentDTO.getContent())
                .posted_time(commentDTO.getPosted_time())
                .build();
    }

    public static CommentDTO toDTO(CommentEntity commentEntity) {
        return CommentDTO.builder()
                .id(commentEntity.getId())
                .author(commentEntity.getAuthor())
                .content(commentEntity.getContent())
                .posted_time(commentEntity.getPosted_time())
                .build();
    }

}
