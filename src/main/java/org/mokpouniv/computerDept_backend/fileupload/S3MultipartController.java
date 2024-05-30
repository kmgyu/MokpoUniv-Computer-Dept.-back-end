package org.mokpouniv.computerDept_backend.fileupload;

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
public class S3MultipartController {
    private final S3MultipartService s3MultipartService;

    @Value("${cloud.aws.s3.bucket}")
    private String videoBucket;

    /**
     * 멀티파트 업로드 시작
     * 업로드 ID를 반환하는데, 업로드 ID는 부분 업로드, 업로드 완료 및 중지할 때 사용된다.
     * @param s3UploadInitiateDto
     * @return
     */
    @PostMapping("/initiate-upload")
    public S3UploadDto initiateUpload(@RequestBody S3UploadInitiateDto s3UploadInitiateDto) {
        return s3MultipartService.initiateUpload(s3UploadInitiateDto.getFileName(), videoBucket, S3Config.videoFolder);
    }

    /**
     * 부분 업로드를 위한 서명된 URL 발급 요청
     * @param s3UploadSignedUrlDto
     * @return
     */
    @PostMapping("/upload-signed-url")
    public S3PresignedUrlDto getUploadSignedUrl(@RequestBody S3UploadSignedUrlDto s3UploadSignedUrlDto){
        return s3MultipartService.getUploadSignedUrl(s3UploadSignedUrlDto, videoBucket, S3Config.videoFolder);
    }

    /**
     * 멀티파트 업로드 완료 요청
     * @param s3UploadCompleteDto
     * @return
     */
    @PostMapping("/complete-upload")
    public S3UploadResultDto completeUpload(@RequestBody S3UploadCompleteDto s3UploadCompleteDto){
        return s3MultipartService.completeUpload(s3UploadCompleteDto, videoBucket, S3Config.videoFolder);
    }

    /**
     * 멀티파트 업로드 중지
     * @param s3UploadAbortDto
     * @return
     */
    @PostMapping("/abort-upload")
    public Void abortUpload(@RequestBody S3UploadAbortDto s3UploadAbortDto){
        s3MultipartService.abortUpload(s3UploadAbortDto, videoBucket, S3Config.videoFolder);
        return null;
    }

    /**
     * 실제 파일 업로드 엔드포인트
     * @param file
     * @return
     */
    @PostMapping("/actual-upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        s3MultipartService.uploadFile(file, videoBucket, S3Config.videoFolder);
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }
    /**
     * 파일 다운로드를 위한 서명된 URL 발급 요청
     * @param fileName
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<String> generateDownloadUrl(@RequestParam("fileName") String fileName) {
        String downloadUrl = s3MultipartService.generateDownloadUrl(fileName, videoBucket, S3Config.videoFolder);
        return ResponseEntity.ok(downloadUrl);
    }
}