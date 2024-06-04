package org.mokpouniv.computerDept_backend.forum.market;

import org.mokpouniv.computerDept_backend.forum.comment.CommentMapper;

import java.util.stream.Collectors;

public class MarketMapper {
    /**
     * question에 대한 DTO를 Entity로 변환한다.
     * @param marketEntity
     * @return
     */
    public static MarketDetailDTO toDetailDTO (MarketEntity marketEntity) {
        return MarketDetailDTO.builder()
                .id(marketEntity.getId())
                .title(marketEntity.getTitle())
                .view(marketEntity.getView())
                .author(marketEntity.getAuthor())
                .content(marketEntity.getContent())
                .posted_time(marketEntity.getPosted_time())
                .isAnswer(marketEntity.isAnswer())
                .commentDTOList(marketEntity.getCommentEntityList().stream()
                        .map(CommentMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    /**
     * question에 대한 Entity를 DTO로 변환한다.
     * @param marketDetailDTO
     * @return
     */
    public static MarketEntity toEntity (MarketDetailDTO marketDetailDTO) {
        return MarketEntity.builder()
                .id(marketDetailDTO.getId())
                .title(marketDetailDTO.getTitle())
                .view(marketDetailDTO.getView())
                .author(marketDetailDTO.getAuthor())
                .content(marketDetailDTO.getContent())
                .posted_time(marketDetailDTO.getPosted_time())
                .isAnswer(marketDetailDTO.isAnswer())
                .commentEntityList(marketDetailDTO.getCommentDTOList().stream()
                        .map(CommentMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

}
