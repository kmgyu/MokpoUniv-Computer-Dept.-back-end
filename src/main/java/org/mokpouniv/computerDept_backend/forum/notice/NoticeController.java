package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/notice")
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    /**
     * 게시글을 만듬 json형태로 보여줌 ( id, title, author, content )
     * @param noticeDetailDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<NoticeDetailDTO> createNotice(@RequestBody NoticeDetailDTO noticeDetailDTO) {
        String noticeId = noticeService.saveNotice(noticeDetailDTO);
        if (noticeId != null ) {
            return ResponseEntity.created(URI.create("/notice/" + noticeId))
                    .build();
        }
        else { return ResponseEntity.badRequest().build(); }
    }


    /**
     * 제목을 기준으로 관련 게시글 전부 json형태로 보여줌
     * @param title
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<PagedModel<NoticeSummaryDTO>> searchNotices(@RequestParam(value = "page") int page,
                                                                      @RequestParam(value = "title", required = false) String title,
                                                                      @RequestParam(value = "author", required = false) String author) {
        Page<NoticeSummaryDTO> results = null;
        if (title != null && !title.isEmpty()) {
            results = noticeService.findNoticeByTitle(page, title);
        } else if (author != null && !author.isEmpty()) {
            results = noticeService.findNoticeByAuthor(page, author);
        } else if (author == null && title == null) {
            results = noticeService.findAllNotice(page);
        } else {
            return ResponseEntity.badRequest().build();
        }

        if (results.getNumberOfElements() == 0) {
            return ResponseEntity.noContent().build();
        }
        PagedModel<NoticeSummaryDTO> paged = new PagedModel<>(results);
        return ResponseEntity.ok(paged);
    }

    /**
     * id를 기준으로 해당 게시글 삭제 -> true, false 반환
     * json으로 message를 반환하도록 refactoring 필요
     * pathVariable을 써야하는가?
     * @param id
     * @return boolean
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable String id) {
        if (noticeService.deleteNotice(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * id를 기준으로 해당 게시글 제목과 내용만 수정 가능 json형태로 보여줌
     * 수정한 내용을 반환하며, 없으면 null 반환.
     * @param noticeDetailDTO
     * @param id
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<NoticeDetailDTO> updateNotice(@RequestBody NoticeDetailDTO noticeDetailDTO,
                                                     @PathVariable String id) {
        NoticeDetailDTO response = noticeService.updateNotice(id, noticeDetailDTO);

        if (response != null) {
            return ResponseEntity.ok(noticeDetailDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * notice의 페이지 상세 데이터 반환
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public NoticeDetailDTO getDetailNotice(@PathVariable String id) {
        return noticeService.findNoticeById(id);
    }
    // 모든 게시글의 제목만 보여줌
//    @GetMapping("/get-names")
//    public List<String> getNames() { return noticeService.getItemNames(); }


}
