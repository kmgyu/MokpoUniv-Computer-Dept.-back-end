package org.mokpouniv.computerDept_backend.forum.qna.comment;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@Api(tags ="Comment API")

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
    @Operation(summary = "Create a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
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
    @Operation(summary = "Create a comment for an answer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
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