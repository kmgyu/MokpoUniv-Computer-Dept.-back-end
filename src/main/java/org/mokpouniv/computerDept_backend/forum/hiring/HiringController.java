package org.mokpouniv.computerDept_backend.forum.hiring;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/hiring")
@RestController
public class HiringController {
    private final HiringService hiringService;

    /**
     * question을 생성한다.
     * @param hiringDetailDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<HiringDetailDTO> createQuestion(
            @RequestBody HiringDetailDTO hiringDetailDTO) {
        String questionId = hiringService.saveQuestion(hiringDetailDTO);

        if (questionId != null) {
            return ResponseEntity.created(URI.create("/question/" + questionId)).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * 주의 : Entity는 댓글을 저장하지 않는다. 댓글 리포지토리를 활용해 따로 받아온다.
     * question의 상세 내용을 얻는다.
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<HiringDetailDTO> getDetailQuestion(@PathVariable String id) {
        HiringDetailDTO hiringDetailDTO = hiringService.searchQuestionById(id);

        return ResponseEntity.ok(hiringDetailDTO);
    }

    /**
     * question의 목록을 받는다.
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<HiringDetailDTO>> getQuestions() {
        List<HiringDetailDTO> hiringDetailDTOS = hiringService.searchQuestionByTitle("");
        return ResponseEntity.ok(hiringDetailDTOS);
    }
}
