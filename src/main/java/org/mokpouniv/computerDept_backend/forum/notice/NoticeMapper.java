package org.mokpouniv.computerDept_backend.forum.notice;

public class NoticeMapper {
    public static NoticeSummaryDTO toSummaryDTO(NoticeEntity noticeEntity) {
        return NoticeSummaryDTO.builder()
                .id(noticeEntity.getId())
                .author(noticeEntity.getAuthor())
                .title(noticeEntity.getTitle())
                .posted_time(noticeEntity.getPosted_time())
                .view(noticeEntity.getView())
                .file(!noticeEntity.getFileDTOList().isEmpty())
                .build();
    }

    public static NoticeDetailDTO toDetailDTO(NoticeEntity noticeEntity) {
        return NoticeDetailDTO.builder()
                .id(noticeEntity.getId())
                .author(noticeEntity.getAuthor())
                .title(noticeEntity.getTitle())
                .content(noticeEntity.getContent())
                .posted_time(noticeEntity.getPosted_time())
                .fileDTOList(noticeEntity.getFileDTOList())
                .view(noticeEntity.getView())
                .build();
    }

    public static NoticeEntity toEntity(NoticeDetailDTO noticeDetailDTO) {
        return NoticeEntity.builder()
                .id(noticeDetailDTO.getId())
                .title(noticeDetailDTO.getTitle())
                .content(noticeDetailDTO.getContent())
                .author(noticeDetailDTO.getAuthor())
                .view(noticeDetailDTO.getView())
                .posted_time(noticeDetailDTO.getPosted_time())
                .fileDTOList(noticeDetailDTO.getFileDTOList())
                .build();
    }
}
