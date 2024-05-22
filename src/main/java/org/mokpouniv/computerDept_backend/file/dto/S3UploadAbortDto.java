package org.mokpouniv.computerDept_backend.file.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class S3UploadAbortDto {
    // initiateUpload에서 얻어온 upload ID
    private String uploadId;

    // initiateUpload에서 얻어온 새 파일명
    private String fileName;
}
