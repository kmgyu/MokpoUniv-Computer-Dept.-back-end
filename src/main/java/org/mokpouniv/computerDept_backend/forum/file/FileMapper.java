package org.mokpouniv.computerDept_backend.forum.file;

public class FileMapper {
    public static FileDTO toDTO(FileEntity fileEntity) {
        return FileDTO.builder()
                .fileName(fileEntity.getFileName())
                .fileUrl(fileEntity.getFileUrl())
                .number(fileEntity.getNumber())
                .build();
    }

    public static FileEntity toEntity(FileDTO fileDTO) {
        return FileEntity.builder()
                .fileName(fileDTO.getFileName())
                .fileUrl(fileDTO.getFileUrl())
                .number(fileDTO.getNumber())
                .build();
    }
}
