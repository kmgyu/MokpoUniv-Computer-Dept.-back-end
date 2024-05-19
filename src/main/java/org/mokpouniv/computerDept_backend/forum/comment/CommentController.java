package org.mokpouniv.computerDept_backend.forum.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{questionId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable String questionId) {
        List<CommentDTO> resultList = commentService.findCommentByQuestionId(questionId);

        if (resultList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(resultList);
        }
    }

    @PostMapping("/{questionId}")
    public ResponseEntity<CommentDTO> createComment(
            @PathVariable String questionId,
            @RequestBody CommentDTO commentDTO
    ) {
        CommentDTO inputDTO = commentDTO;
        inputDTO.setQuestion_id(questionId);
        CommentDTO result = commentService.saveComment(inputDTO, questionId);
        return ResponseEntity.ok(result);
    }


}
