package org.mokpouniv.computerDept_backend.forum.notice;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoticeDetailDTO {
    // 공지는 댓글이 없다.
    // 서비스에서 id를 생성해준다.
    private String id;

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


    public NoticeEntity toNoticeEntity() {
        return NoticeEntity.
            builder()
                .id(id)
                .title(title)
                .author(author)
                .content(content)
                .posted_time(posted_time)
                .view(view)
                .fileDTOList(fileDTOList)
                .build();
    }
}