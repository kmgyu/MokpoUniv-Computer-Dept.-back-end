package org.mokpouniv.computerDept_backend.forum.photo;

public class PhotoMapper {
    public static PhotoSummaryDTO toSummaryDTO(PhotoEntity photoEntity) {
        return PhotoSummaryDTO.builder()
                .id(photoEntity.getId())
                .title(photoEntity.getTitle())
                .view(photoEntity.getView())
                .thumbnail(photoEntity.getImages().get(0))
                .posted_time(photoEntity.getPosted_time())
                .author(photoEntity.getAuthor())
                .build();
    }

    public static PhotoEntity toEntity(PhotoDetailDTO photoDetailDTO) {
        return PhotoEntity.builder()
                .id(photoDetailDTO.getId())
                .title(photoDetailDTO.getTitle())
                .images(photoDetailDTO.getImages())
                .view(photoDetailDTO.getView())
                .posted_time(photoDetailDTO.getPosted_time())
                .author(photoDetailDTO.getAuthor())
                .build();
    }

    public static PhotoDetailDTO toDetailDTO(PhotoEntity photoEntity) {
        return PhotoDetailDTO.builder()
                .id(photoEntity.getId())
                .title(photoEntity.getTitle())
                .view(photoEntity.getView())
                .images(photoEntity.getImages())
                .posted_time(photoEntity.getPosted_time())
                .author(photoEntity.getAuthor())
                .build();
    }
}
