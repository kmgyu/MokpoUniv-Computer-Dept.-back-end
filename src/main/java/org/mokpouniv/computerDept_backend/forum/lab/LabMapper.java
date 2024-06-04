package org.mokpouniv.computerDept_backend.forum.lab;

import org.mokpouniv.computerDept_backend.forum.comment.CommentMapper;

import java.util.stream.Collectors;

public class LabMapper {
    /**
     * question에 대한 DTO를 Entity로 변환한다.
     * @param labEntity
     * @return
     */
    public static LabDetailDTO toDetailDTO (LabEntity labEntity) {
        return LabDetailDTO.builder()
                .id(labEntity.getId())
                .title(labEntity.getTitle())
                .view(labEntity.getView())
                .author(labEntity.getAuthor())
                .content(labEntity.getContent())
                .posted_time(labEntity.getPosted_time())
                .isAnswer(labEntity.isAnswer())
                .commentDTOList(labEntity.getCommentEntityList().stream()
                        .map(CommentMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    /**
     * question에 대한 Entity를 DTO로 변환한다.
     * @param labDetailDTO
     * @return
     */
    public static LabEntity toEntity (LabDetailDTO labDetailDTO) {
        return LabEntity.builder()
                .id(labDetailDTO.getId())
                .title(labDetailDTO.getTitle())
                .view(labDetailDTO.getView())
                .author(labDetailDTO.getAuthor())
                .content(labDetailDTO.getContent())
                .posted_time(labDetailDTO.getPosted_time())
                .isAnswer(labDetailDTO.isAnswer())
                .commentEntityList(labDetailDTO.getCommentDTOList().stream()
                        .map(CommentMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

}
