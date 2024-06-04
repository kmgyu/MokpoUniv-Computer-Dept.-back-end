package org.mokpouniv.computerDept_backend.forum.hiring;

import org.mokpouniv.computerDept_backend.forum.comment.CommentMapper;

import java.util.stream.Collectors;

public class HiringMapper {
    /**
     * question에 대한 DTO를 Entity로 변환한다.
     * @param hiringEntity
     * @return
     */
    public static HiringDetailDTO toDetailDTO (HiringEntity hiringEntity) {
        return HiringDetailDTO.builder()
                .id(hiringEntity.getId())
                .title(hiringEntity.getTitle())
                .view(hiringEntity.getView())
                .author(hiringEntity.getAuthor())
                .content(hiringEntity.getContent())
                .posted_time(hiringEntity.getPosted_time())
                .isAnswer(hiringEntity.isAnswer())
                .commentDTOList(hiringEntity.getCommentEntityList().stream()
                        .map(CommentMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    /**
     * question에 대한 Entity를 DTO로 변환한다.
     * @param hiringDetailDTO
     * @return
     */
    public static HiringEntity toEntity (HiringDetailDTO hiringDetailDTO) {
        return HiringEntity.builder()
                .id(hiringDetailDTO.getId())
                .title(hiringDetailDTO.getTitle())
                .view(hiringDetailDTO.getView())
                .author(hiringDetailDTO.getAuthor())
                .content(hiringDetailDTO.getContent())
                .posted_time(hiringDetailDTO.getPosted_time())
                .isAnswer(hiringDetailDTO.isAnswer())
                .commentEntityList(hiringDetailDTO.getCommentDTOList().stream()
                        .map(CommentMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

}
