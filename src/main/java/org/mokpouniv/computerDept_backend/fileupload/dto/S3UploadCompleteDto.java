package org.mokpouniv.computerDept_backend.fileupload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class S3UploadCompleteDto {
    // initiateUpload에서 얻어온 upload ID
    private String uploadId;

    // initiateUpload에서 얻어온 새 파일명
    private String fileName;

    // 업로드할 파일의 ETag, PartNumber 데이터 목록
    private List<S3UploadPartsDetailDto> parts;
}