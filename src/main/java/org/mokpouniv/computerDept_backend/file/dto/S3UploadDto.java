package org.mokpouniv.computerDept_backend.file.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class S3UploadDto {

    // S3 UploadId
    private String uploadId;
    // 서버에서 생성한 파일 이름
    private String fileName;
}
