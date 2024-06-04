package org.mokpouniv.computerDept_backend.forum.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/lab")
@RestController
public class LabController {
    private final LabService labService;

    /**
     * question을 생성한다.
     * @param labDetailDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<LabDetailDTO> createQuestion(
            @RequestBody LabDetailDTO labDetailDTO) {
        String questionId = labService.saveQuestion(labDetailDTO);

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
    public ResponseEntity<LabDetailDTO> getDetailQuestion(@PathVariable String id) {
        LabDetailDTO labDetailDTO = labService.searchQuestionById(id);

        return ResponseEntity.ok(labDetailDTO);
    }

    /**
     * question의 목록을 받는다.
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<LabDetailDTO>> getQuestions() {
        List<LabDetailDTO> labDetailDTOS = labService.searchQuestionByTitle("");
        return ResponseEntity.ok(labDetailDTOS);
    }
}
