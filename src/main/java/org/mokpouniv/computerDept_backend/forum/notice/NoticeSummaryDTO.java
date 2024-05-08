package org.mokpouniv.computerDept_backend.forum.notice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/**
 * summary DTO
 * 요약된 DTO. 게시글 목록 반환 시 사용.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NoticeSummaryDTO {
    @NotNull(message = "게시글 id를 찾을 수 없습니다.")
    private String id;

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "사용자 인식이 되지 않습니다.")
    private String author;

    @NotNull(message = "시간이 없습니다.")
    private LocalDateTime posted_time;

//    파일 유무
    private Boolean file;

    private int view;

    public NoticeEntity toNoticeEntity() {
        return NoticeEntity.
                builder().
                id(id).
                title(title)
                .author(author)
                .posted_time(posted_time)
                .build();
    }


}
