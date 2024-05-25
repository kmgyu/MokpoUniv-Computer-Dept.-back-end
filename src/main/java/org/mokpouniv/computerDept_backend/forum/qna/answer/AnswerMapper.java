package org.mokpouniv.computerDept_backend.forum.qna.answer;

import org.mokpouniv.computerDept_backend.forum.qna.comment.CommentMapper;

import java.util.stream.Collectors;

public class AnswerMapper {
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
