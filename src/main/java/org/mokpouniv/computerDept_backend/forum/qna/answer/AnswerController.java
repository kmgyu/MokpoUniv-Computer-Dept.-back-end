package org.mokpouniv.computerDept_backend.forum.qna.answer;

import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/answer")
@RequiredArgsConstructor
@RestController
public class AnswerController {
    private final AnswerService answerService;

    /**
     * Answer를 작성함.
     * @param questionId
     * @param answerDTO
     * @return
     */
    @PostMapping("/{questionId}")
    public ResponseEntity<AnswerDTO> createAnswer (
            @PathVariable String questionId,
            @RequestBody AnswerDTO answerDTO
    ) {
        String result = answerService.saveAnswer(answerDTO, questionId);
        return ResponseEntity.created(URI.create("/question/" + questionId)).build();
    }
}
