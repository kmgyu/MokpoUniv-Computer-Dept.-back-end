package org.mokpouniv.computerDept_backend.forum.qna.answer;

import org.mokpouniv.computerDept_backend.forum.qna.comment.CommentMapper;

import java.util.stream.Collectors;

public class AnswerMapper {
    /**
     * AnswerDTO를 AnswerEntity로 변환함.
     * @param answerDTO
     * @return
     */
    public static AnswerEntity toEntity(AnswerDTO answerDTO) {
        return AnswerEntity.builder()
                .id(answerDTO.getId())
                .author(answerDTO.getAuthor())
                .content(answerDTO.getContent())
                .posted_time(answerDTO.getPosted_time())
                .commentEntityList(answerDTO.getCommentDTOList().stream()
                        .map(CommentMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

    /**
     * AnswerEntity를 AnswerDTO로 변환함.
     * @param answerEntity
     * @return
     */
    public static AnswerDTO toDTO(AnswerEntity answerEntity) {
        return AnswerDTO.builder()
                .id(answerEntity.getId())
                .author(answerEntity.getAuthor())
                .content(answerEntity.getContent())
                .posted_time(answerEntity.getPosted_time())
                .commentDTOList(answerEntity.getCommentEntityList().stream()
                        .map(CommentMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
