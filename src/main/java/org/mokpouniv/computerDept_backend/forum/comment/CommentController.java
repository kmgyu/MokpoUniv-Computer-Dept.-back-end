package org.mokpouniv.computerDept_backend.forum.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    /**
     * questionId에 대해 검색후, 해당 question에 댓글 작성
     * @param questionId
     * @param commentDTO
     * @return
     */
    @PostMapping("/{questionId}")
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable String questionId,
            @RequestBody CommentDTO commentDTO
    ) {
        String result = commentService.saveQuestionComment(commentDTO, questionId);

        return ResponseEntity.created(URI.create("/question/" + questionId)).build();
    }

    /**
     * questionId에 대해 검색후, 해당 question에서 answerId를 가진 answer에 대해 댓글 작성
     * @param questionId
     * @param answerId
     * @param commentDTO
     * @return
     */
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
