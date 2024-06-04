package org.mokpouniv.computerDept_backend.forum.market;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/market")
@RestController
public class MarketController {
    private final MarketService marketService;

    /**
     * question을 생성한다.
     * @param marketDetailDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<MarketDetailDTO> createQuestion(
            @RequestBody MarketDetailDTO marketDetailDTO) {
        String questionId = marketService.saveQuestion(marketDetailDTO);

        if (questionId != null) {
            return ResponseEntity.created(URI.create("/market/" + questionId)).build();
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
    public ResponseEntity<MarketDetailDTO> getDetailQuestion(@PathVariable String id) {
        MarketDetailDTO marketDetailDTO = marketService.searchQuestionById(id);

        return ResponseEntity.ok(marketDetailDTO);
    }

    /**
     * question의 목록을 받는다.
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<MarketDetailDTO>> getQuestions() {
        List<MarketDetailDTO> marketDetailDTOS = marketService.searchQuestionByTitle("");
        return ResponseEntity.ok(marketDetailDTOS);
    }
}
