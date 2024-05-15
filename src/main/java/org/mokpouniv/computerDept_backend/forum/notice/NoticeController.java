package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/notice")
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    // 게시글을 만듬 json형태로 보여줌 ( id, title, author, content )
    @PostMapping("/create")
    public ResponseEntity<NoticeDetailDTO> createNotice(@RequestBody NoticeDetailDTO noticeDetailDTO) {
        String noticeId = noticeService.addNotice(noticeDetailDTO);
        if (noticeId != null ) {
            return ResponseEntity.created(URI.create("/notice/" + noticeId))
                    .build();
        }
        else { return ResponseEntity.badRequest().build(); }
    }

    // 제목을 기준으로 관련 게시글 전부 json형태로 보여줌
    @GetMapping("/")
    public ResponseEntity<List<NoticeSummaryDTO>> searchNotices(@RequestParam("title") String title) {
        List<NoticeSummaryDTO> results = noticeService.searchNoticeByTitle(title);
        return ResponseEntity.ok(results);
    }

    /**
     * id를 기준으로 해당 게시글 삭제 -> true, false 반환
     * json으로 message를 반환하도록 refactoring 필요
     * pathVariable을 써야하는가?
     * @param id
     * @return boolean
     */
    @DeleteMapping("/")
    public ResponseEntity<?> deleteNotice(@RequestParam("id") String id) {
        if (noticeService.deleteNotice(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
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
        if (response != null) { return ResponseEntity.ok(noticeDetailDTO); }
        else { return ResponseEntity.notFound().build(); }
    }

    /**
     * notice의 페이지 상세 데이터 반환
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public NoticeDetailDTO getDetailNotice(@RequestParam String id) {
        return noticeService.searchNoticeById(id);
    }
    // 모든 게시글의 제목만 보여줌
//    @GetMapping("/get-names")
//    public List<String> getNames() { return noticeService.getItemNames(); }


}
