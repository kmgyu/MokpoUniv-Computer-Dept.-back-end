package org.mokpouniv.computerDept_backend.forum.qna.comment;

public class CommentMapper {
    /**
     * Comment에 대해, DTO를 Entity로 변환
     * @param commentDTO
     * @return
     */
    public static CommentEntity toEntity(CommentDTO commentDTO) {
        return CommentEntity.builder()
                .id(commentDTO.getId())
                .author(commentDTO.getAuthor())
                .content(commentDTO.getContent())
                .posted_time(commentDTO.getPosted_time())
                .build();
    }

    /**
     * Comment에 대해, Entity를 DTO로 변환
     * @param commentEntity
     * @return
     */
    public static CommentDTO toDTO(CommentEntity commentEntity) {
        return CommentDTO.builder()
                .id(commentEntity.getId())
                .author(commentEntity.getAuthor())
                .content(commentEntity.getContent())
                .posted_time(commentEntity.getPosted_time())
                .build();
    }

}
