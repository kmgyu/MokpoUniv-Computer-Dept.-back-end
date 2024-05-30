package org.mokpouniv.computerDept_backend.forum.qna.question;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/question")
@RestController
public class QuestionController {
    private final QuestionService questionService;

    /**
     * question을 생성한다.
     * @param questionDetailDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<QuestionDetailDTO> createQuestion(
            @RequestBody QuestionDetailDTO questionDetailDTO) {
        String questionId = questionService.saveQuestion(questionDetailDTO);

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
    public ResponseEntity<QuestionDetailDTO> getDetailQuestion(@PathVariable String id) {
        QuestionDetailDTO questionDetailDTO = questionService.searchQuestionById(id);

        return ResponseEntity.ok(questionDetailDTO);
    }

    /**
     * question의 목록을 받는다.
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<QuestionDetailDTO>> getQuestions() {
        List<QuestionDetailDTO> questionDetailDTOS = questionService.searchQuestionByTitle("");
        return ResponseEntity.ok(questionDetailDTOS);
    }
}
