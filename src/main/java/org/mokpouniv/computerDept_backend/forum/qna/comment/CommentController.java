package org.mokpouniv.computerDept_backend.forum.qna.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{questionId}")
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable String questionId,
            @RequestBody CommentDTO commentDTO
    ) {
        String result = commentService.saveQuestionComment(commentDTO, questionId);

        return ResponseEntity.created(URI.create("/question/" + questionId)).build();
    }

    @PostMapping("/{questionId}/{answerId}")
    public ResponseEntity<CommentDTO> createAnswerComment(
            @PathVariable String questionId,
            @PathVariable String answerId,
            @RequestBody CommentDTO commentDTO
    ) {
        String result = commentService.saveAnswerComment(commentDTO, questionId, answerId);
        return ResponseEntity.created(URI.create("/question/" + questionId)).build();
    }

}
