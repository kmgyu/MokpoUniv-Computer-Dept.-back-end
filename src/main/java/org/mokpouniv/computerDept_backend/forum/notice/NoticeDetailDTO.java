package org.mokpouniv.computerDept_backend.forum.notice;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.mokpouniv.computerDept_backend.forum.file.FileDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoticeDetailDTO {
    // 공지는 댓글이 없다.
    // default로 uuid 생성
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "사용자 인식이 되지 않습니다.")
    private String author;

    @NotBlank(message = "내용이 없습니다.")
    private String content;

    //아래는 Builder로 생성해주지 않아도 되는 데이터들

    /**
     * 현재 서버 시간을 자동으로 생성해줌.
     */
    @Builder.Default
    private LocalDateTime posted_time = LocalDateTime.now();

    /**
     * 파일은 따로 처리 안했지만 첨부파일에 대한 처리가 따로 필요함.
     */
    @Builder.Default
    private List<FileDTO> fileDTOList = null;

    @Builder.Default
    private int view = 0; // 조회수


}