package org.mokpouniv.computerDept_backend.forum.qna;

public class QuestionMapper {
    public static QuestionDetailDTO toDetailDTO (QuestionEntity questionEntity) {
        return QuestionDetailDTO.builder()
                .id(questionEntity.getId())
                .title(questionEntity.getTitle())
                .view(questionEntity.getView())
                .author(questionEntity.getAuthor())
                .content(questionEntity.getContent())
                .posted_time(questionEntity.getPosted_time())
                .build();
    }

    public static QuestionEntity toEntity (QuestionDetailDTO questionDetailDTO) {
        return QuestionEntity.builder()
                .id(questionDetailDTO.getId())
                .title(questionDetailDTO.getTitle())
                .view(questionDetailDTO.getView())
                .author(questionDetailDTO.getAuthor())
                .content(questionDetailDTO.getContent())
                .posted_time(questionDetailDTO.getPosted_time())
                .build();
    }

}
