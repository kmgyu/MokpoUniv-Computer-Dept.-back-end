package org.mokpouniv.computerDept_backend.file;

import org.mokpouniv.computerDept_backend.file.dto.*;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3MultipartService {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final AmazonS3Client amazonS3Client;

    public S3UploadDto initiateUpload(String originalFileName, String targetBucket, String targetObjectDir) {
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
        String newFileName = System.currentTimeMillis() + fileType;
        Instant now = Instant.now();

        CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
                .bucket(targetBucket)
                .key(targetObjectDir + "/" + newFileName)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .expires(now.plusSeconds(60 * 20))
                .build();

        CreateMultipartUploadResponse response = s3Client.createMultipartUpload(createMultipartUploadRequest);
        return new S3UploadDto(response.uploadId(), newFileName);
    }

    public S3PresignedUrlDto getUploadSignedUrl(S3UploadSignedUrlDto s3UploadSignedUrlDto, String targetBucket, String targetObjectDir) {
        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(targetBucket)
                .key(targetObjectDir + "/" + s3UploadSignedUrlDto.getFileName())
                .partNumber(s3UploadSignedUrlDto.getPartNumber())
                .build();

        UploadPartPresignRequest uploadPartPresignRequest = UploadPartPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .uploadPartRequest(uploadPartRequest)
                .build();

        PresignedUploadPartRequest presignedUploadPartRequest = s3Presigner.presignUploadPart(uploadPartPresignRequest);
        return new S3PresignedUrlDto(presignedUploadPartRequest.url().toString());
    }

    public S3UploadResultDto completeUpload(S3UploadCompleteDto s3UploadCompleteDto, String targetBucket, String targetObjectDir) {
        List<CompletedPart> completedParts = new ArrayList<>();
        for (S3UploadPartsDetailDto partForm : s3UploadCompleteDto.getParts()) {
            CompletedPart part = CompletedPart.builder()
                    .partNumber(partForm.getPartNumber())
                    .eTag(partForm.getAwsETag())
                    .build();
            completedParts.add(part);
        }

        CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder()
                .parts(completedParts)
                .build();
        String fileName = s3UploadCompleteDto.getFileName();
        CompleteMultipartUploadRequest completedMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
                .bucket(targetBucket)
                .key(targetObjectDir + "/" + fileName)
                .uploadId(s3UploadCompleteDto.getUploadId())
                .multipartUpload(completedMultipartUpload)
                .build();

        CompleteMultipartUploadResponse completeMultipartUploadResponse =
                s3Client.completeMultipartUpload(completedMultipartUploadRequest);
        String objectKey = completeMultipartUploadResponse.key();
        String url = amazonS3Client.getUrl(targetBucket, objectKey).toString();
        String bucket = completeMultipartUploadResponse.bucket();

        long fileSize = getFileSizeFromS3Url(bucket, objectKey);

        return S3UploadResultDto.builder()
                .name(fileName)
                .url(url)
                .size(fileSize)
                .build();
    }

    public void abortUpload(S3UploadAbortDto s3UploadAbortDto, String targetBucket, String targetObjectDir) {
        AbortMultipartUploadRequest abortMultipartUploadRequest = AbortMultipartUploadRequest.builder()
                .bucket(targetBucket)
                .key(targetObjectDir + "/" + s3UploadAbortDto.getFileName())
                .uploadId(s3UploadAbortDto.getUploadId())
                .build();

        s3Client.abortMultipartUpload(abortMultipartUploadRequest);
    }

    private long getFileSizeFromS3Url(String bucketName, String fileName) {
        GetObjectMetadataRequest metadataRequest = new GetObjectMetadataRequest(bucketName, fileName);
        ObjectMetadata objectMetadata = amazonS3Client.getObjectMetadata(metadataRequest);
        return objectMetadata.getContentLength();
    }

    public void uploadFile(MultipartFile file, String bucketName, String folderName) {
        try {
            String fileName = folderName + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream(), new ObjectMetadata())
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3Client.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }
}