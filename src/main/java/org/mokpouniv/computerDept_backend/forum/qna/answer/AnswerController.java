package org.mokpouniv.computerDept_backend.forum.qna.answer;

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
@Api(tags = "Answer API")
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
    @Operation(summary = "Create answer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Answer created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/{questionId}")
    public ResponseEntity<AnswerDTO> createAnswer(
            @PathVariable String questionId,
            @RequestBody AnswerDTO answerDTO
    ) {
        String result = answerService.saveAnswer(answerDTO, questionId);
        return ResponseEntity.created(URI.create("/question/" + questionId)).build();
    }
}