package org.mokpouniv.computerDept_backend.fileupload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class S3UploadInitiateDto {
    // 업로드할 파일의 originFileName
    private String fileName;
}
