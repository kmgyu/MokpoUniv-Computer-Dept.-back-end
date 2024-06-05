package org.mokpouniv.computerDept_backend.forum.qna.question;

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
import java.util.List;
@Api(tags = "Question API")

@RequiredArgsConstructor
@RequestMapping("/question")
@RestController
public class QuestionController {
    private final QuestionService questionService;

    /**
     * question을 생성한다.
     *
     * @param questionDetailDTO
     * @return
     */
    @Operation(summary = "Create a new Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Question created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionDetailDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
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
     *
     * @return
     */
    @Operation(summary = "Get a DetailQuestion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question details retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionDetailDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Question not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDetailDTO> getDetailQuestion(@PathVariable String id) {
        QuestionDetailDTO questionDetailDTO = questionService.searchQuestionById(id);

        return ResponseEntity.ok(questionDetailDTO);
    }

    /**
     * question의 목록을 받는다.
     *
     * @return
     */
    @Operation(summary = "Get a Question List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question list retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionDetailDTO.class, type = "array"))}),
            @ApiResponse(responseCode = "404", description = "No questions found")
    })
    @GetMapping("/")
    public ResponseEntity<List<QuestionDetailDTO>> getQuestions() {
        List<QuestionDetailDTO> questionDetailDTOS = questionService.searchQuestionByTitle("");
        return ResponseEntity.ok(questionDetailDTOS);
    }
}
