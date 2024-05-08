package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("Notice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoticeEntity {

    @Id
    private String id;  //식별아이디

    private String title; // 게시물 제목

    private String author; // 게시물 작성자

    private String content; // 게시물 내용

    private int view; // 조회수

    private LocalDateTime posted_time;

    /**
     * File에 대한 데이터를 따로 저장하는 테이블 필요?
     * 혹은 document안에 한꺼번에 저장?
     * @return
     */
    private List<FileDTO> fileDTOList;

    public NoticeDetailDTO toNoticeDetailDTO() {
        return NoticeDetailDTO.
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

    public NoticeSummaryDTO toNoticeSummaryDTO() {
        return NoticeSummaryDTO
                .builder()
                .id(id)
                .title(title)
                .posted_time(posted_time)
                .author(author)
                .view(view)
                .file(!fileDTOList.isEmpty())
                .build();
    }

}