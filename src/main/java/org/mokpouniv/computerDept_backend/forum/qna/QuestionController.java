package org.mokpouniv.computerDept_backend.forum.qna;

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

    @RequestMapping("/create")
    public ResponseEntity<QuestionDetailDTO> createQuestion(
            @RequestBody QuestionDetailDTO questionDetailDTO) {
        String questionId = questionService.saveQuestion(questionDetailDTO);

        if (questionId != null) {
            return ResponseEntity.created(URI.create("/question/" + questionId)).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDetailDTO> getDetailQuestion(@PathVariable String id) {
        QuestionDetailDTO questionDetailDTO = questionService.searchQuestionById(id);

        return ResponseEntity.ok(questionDetailDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<QuestionDetailDTO>> getQuestions() {
        List<QuestionDetailDTO> questionDetailDTOS = questionService.searchQuestionByTitle("");
        return ResponseEntity.ok(questionDetailDTOS);
    }
}
