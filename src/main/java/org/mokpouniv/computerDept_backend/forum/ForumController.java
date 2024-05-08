package org.mokpouniv.computerDept_backend.forum;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/forum")
@RestController
public class ForumController {

    private final ForumService forumService;

    // DB에서 조회해서 데이터를 가져와야 됨.
    @GetMapping("/list")
    public String list() {
        return "hi";
    }

    // 게시글을 만듬 json형태로 보여줌 ( id, title, author, content )
    @PostMapping("/create")
    public ResponseEntity<ForumEntity> getItemNameForCreate(@RequestBody ForumDTO forumDTO) {
        boolean item = forumService.addItem(forumDTO);
        if (item) { return ResponseEntity.ok(forumDTO.toForumEntity()); }
        else { return ResponseEntity.notFound().build(); }
    }

    // 제목을 기준으로 관련 게시글 전부 json형태로 보여줌
    @GetMapping("/search")
    public ResponseEntity<List<ForumEntity>> getItemNameForSearch(@RequestParam("title") String title) {
        List<ForumEntity> results = forumService.readItemsByTitle(title);
        return ResponseEntity.ok(results);
    }

    // id를 기준으로 해당 게시글 삭제 -> true, false 반환
    @DeleteMapping("/delete")
    public boolean deleteForum(@RequestParam("id") String id) {
        return forumService.deleteItem(id);
    }

    // id를 기준으로 해당 게시글 제목과 내용만 수정 가능 json형태로 보여줌
    @PostMapping("/update")
    public ResponseEntity<ForumEntity> getItemNameForUpdate(@RequestBody ForumDTO forumDTO,
                                                            @RequestParam("id") String id) {
        boolean item = forumService.updateItem(id);
        if (item) { return ResponseEntity.ok(forumDTO.toForumEntity()); }
        else { return ResponseEntity.notFound().build(); }
    }

    // 모든 게시글의 제목만 보여줌
    @GetMapping("/get-names")
    public List<String> getNames() { return forumService.getItemNames(); }
}
