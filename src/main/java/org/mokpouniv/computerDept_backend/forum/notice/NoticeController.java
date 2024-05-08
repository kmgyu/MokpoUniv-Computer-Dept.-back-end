package org.mokpouniv.computerDept_backend.forum.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/notice")
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    // 게시글을 만듬 json형태로 보여줌 ( id, title, author, content )
    @PostMapping("/create")
    public ResponseEntity<NoticeEntity> createNotice(@RequestBody NoticeDetailDTO noticeDetailDTO) {
        boolean item = noticeService.addNotice(noticeDetailDTO);
        if (item) { return ResponseEntity.ok(noticeDetailDTO.toNoticeEntity()); }
        else { return ResponseEntity.notFound().build(); }
    }

    // 제목을 기준으로 관련 게시글 전부 json형태로 보여줌
    @GetMapping("/search")
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
    @DeleteMapping("/delete")
    public boolean deleteNotice(@RequestParam("id") String id) {
        return noticeService.deleteNotice(id);
    }


    /**
     * id를 기준으로 해당 게시글 제목과 내용만 수정 가능 json형태로 보여줌
     * @param noticeDetailDTO
     * @param id
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<NoticeDetailDTO> updateNotice(@RequestBody NoticeDetailDTO noticeDetailDTO,
                                                     @RequestParam("id") String id) {
        boolean response = noticeService.updateNotice(id);
        if (response) { return ResponseEntity.ok(noticeDetailDTO); }
        else { return ResponseEntity.notFound().build(); }
    }

    /**
     * notice의 페이지 상세 데이터 반환
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public NoticeDetailDTO getDetailNotice(@RequestParam String id) {
        return null;
    }
    // 모든 게시글의 제목만 보여줌
//    @GetMapping("/get-names")
//    public List<String> getNames() { return noticeService.getItemNames(); }


}
