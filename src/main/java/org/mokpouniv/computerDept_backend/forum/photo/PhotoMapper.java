package org.mokpouniv.computerDept_backend.forum.photo;

public class PhotoMapper {
    public static PhotoSummaryDTO toDTO(PhotoEntity photoEntity) {
        return PhotoSummaryDTO.builder()
                .id(photoEntity.getId())
                .title(photoEntity.getTitle())
                .view(photoEntity.getView())
                .thumbnail(photoEntity.getThumbnail())
                .posted_time(photoEntity.getPosted_time())
                .author(photoEntity.getAuthor())
                .build();
    }

    public static PhotoEntity toEntity(PhotoSummaryDTO photoSummaryDTO) {
        return PhotoEntity.builder()
                .id(photoSummaryDTO.getId())
                .title(photoSummaryDTO.getTitle())
                .view(photoSummaryDTO.getView())
                .thumbnail(photoSummaryDTO.getThumbnail())
                .posted_time(photoSummaryDTO.getPosted_time())
                .author(photoSummaryDTO.getAuthor())
                .build();
    }
}
