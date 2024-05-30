package org.mokpouniv.computerDept_backend.fileupload.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class S3UploadResultDto {

    // S3 URL
    private String url;

    // 파일 명
    private String name;

    // 원본 파일 사이즈 (바이트)
    private long size;
}