package org.mokpouniv.computerDept_backend.file.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class S3UploadPartsDetailDto {
    private String awsETag;
    private int partNumber;
}