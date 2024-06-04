package org.mokpouniv.computerDept_backend.forum.market;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
//    private final CommentRepository commentRepository;

    /**
     * question을 제목을 기준으로 검색한다. 문자열이 포함되어 있다면 모두 반환한다.
     * @param title
     * @return
     */
    public List<MarketDetailDTO> searchQuestionByTitle(String title) {
        return marketRepository.findQuestionEntitiesByTitle(title).stream()
                .map(MarketMapper::toDetailDTO)
                .collect(Collectors.toList());
    }

//    public List<QuestionDetailDTO> searchQuestionById(String id) {
//        return questionRepository.findQuestionEntitiesByTitle(title).stream()
//                .map(QuestionMapper::toDetailDTO)
//                .collect(Collectors.toList());
//    }

    /**
     * question의 id를 기준으로 검색한다.
     * @param id
     * @return
     */
    public MarketDetailDTO searchQuestionById(String id) {
        MarketEntity marketEntity = marketRepository.findQuestionEntityById(id);

        return MarketMapper.toDetailDTO(marketEntity);
    }

    /**
     * question을 저장한다.
     * @param marketDetailDTO
     * @return
     */
    public String saveQuestion(MarketDetailDTO marketDetailDTO) {

        MarketEntity savedEntity = marketRepository.save(
                MarketMapper.toEntity(marketDetailDTO));

        return MarketMapper.toDetailDTO(savedEntity).getId();
    }


}
