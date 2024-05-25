package org.mokpouniv.computerDept_backend.forum.qna.question;

import org.mokpouniv.computerDept_backend.forum.qna.answer.AnswerMapper;
import org.mokpouniv.computerDept_backend.forum.qna.comment.CommentMapper;

import java.util.stream.Collectors;

public class QuestionMapper {
    /**
     * question에 대한 DTO를 Entity로 변환한다.
     * @param questionEntity
     * @return
     */
    public static QuestionDetailDTO toDetailDTO (QuestionEntity questionEntity) {
        return QuestionDetailDTO.builder()
                .id(questionEntity.getId())
                .title(questionEntity.getTitle())
                .view(questionEntity.getView())
                .author(questionEntity.getAuthor())
                .content(questionEntity.getContent())
                .posted_time(questionEntity.getPosted_time())
                .isAnswer(questionEntity.isAnswer())
                .answerDTOList(questionEntity.getAnswerEntityList().stream()
                        .map(AnswerMapper::toDTO).collect(Collectors.toList()))
                .commentDTOList(questionEntity.getCommentEntityList().stream()
                        .map(CommentMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    /**
     * question에 대한 Entity를 DTO로 변환한다.
     * @param questionDetailDTO
     * @return
     */
    public static QuestionEntity toEntity (QuestionDetailDTO questionDetailDTO) {
        return QuestionEntity.builder()
                .id(questionDetailDTO.getId())
                .title(questionDetailDTO.getTitle())
                .view(questionDetailDTO.getView())
                .author(questionDetailDTO.getAuthor())
                .content(questionDetailDTO.getContent())
                .posted_time(questionDetailDTO.getPosted_time())
                .isAnswer(questionDetailDTO.isAnswer())
                .answerEntityList(questionDetailDTO.getAnswerDTOList().stream()
                        .map(AnswerMapper::toEntity).collect(Collectors.toList()))
                .commentEntityList(questionDetailDTO.getCommentDTOList().stream()
                        .map(CommentMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

}
