package org.mokpouniv.computerDept_backend.fileupload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.config.S3Config;
import org.springframework.beans.factory.annotation.Value;
import org.mokpouniv.computerDept_backend.fileupload.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
@Api(tags = "S3 Multipart Upload")
public class S3MultipartController {
    private final S3MultipartService s3MultipartService;

    @Value("${cloud.aws.s3.bucket}")
    private String videoBucket;

    @ApiOperation("Initiate Multipart Upload")
    @PostMapping("/initiate-upload")
    public S3UploadDto initiateUpload(@RequestBody @ApiParam(value = "Upload Initiation Information") S3UploadInitiateDto s3UploadInitiateDto) {
        return s3MultipartService.initiateUpload(s3UploadInitiateDto.getFileName(), videoBucket, S3Config.videoFolder);
    }

    @ApiOperation("Request Signed URL for Uploading Part")
    @PostMapping("/upload-signed-url")
    public S3PresignedUrlDto getUploadSignedUrl(@RequestBody @ApiParam(value = "Signed URL Request Information") S3UploadSignedUrlDto s3UploadSignedUrlDto){
        return s3MultipartService.getUploadSignedUrl(s3UploadSignedUrlDto, videoBucket, S3Config.videoFolder);
    }

    @ApiOperation("Complete Multipart Upload")
    @PostMapping("/complete-upload")
    public S3UploadResultDto completeUpload(@RequestBody @ApiParam(value = "Upload Completion Information") S3UploadCompleteDto s3UploadCompleteDto){
        return s3MultipartService.completeUpload(s3UploadCompleteDto, videoBucket, S3Config.videoFolder);
    }

    @ApiOperation("Abort Multipart Upload")
    @PostMapping("/abort-upload")
    public Void abortUpload(@RequestBody @ApiParam(value = "Upload Abort Information") S3UploadAbortDto s3UploadAbortDto){
        s3MultipartService.abortUpload(s3UploadAbortDto, videoBucket, S3Config.videoFolder);
        return null;
    }

    @ApiOperation("Upload File")
    @PostMapping("/actual-upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") @ApiParam(value = "File to Upload") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        s3MultipartService.uploadFile(file, videoBucket, S3Config.videoFolder);
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }

    @ApiOperation("Generate Signed URL for Download")
    @GetMapping("/download")
    public ResponseEntity<String> generateDownloadUrl(@RequestParam("fileName") @ApiParam(value = "File Name to Download") String fileName) {
        String downloadUrl = s3MultipartService.generateDownloadUrl(fileName, videoBucket, S3Config.videoFolder);
        return ResponseEntity.ok(downloadUrl);
    }
}