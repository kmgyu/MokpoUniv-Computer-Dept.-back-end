package org.mokpouniv.computerDept_backend.forum.hiring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HiringService {

    private final HiringRepository hiringRepository;
//    private final CommentRepository commentRepository;

    /**
     * question을 제목을 기준으로 검색한다. 문자열이 포함되어 있다면 모두 반환한다.
     * @param title
     * @return
     */
    public List<HiringDetailDTO> searchQuestionByTitle(String title) {
        return hiringRepository.findQuestionEntitiesByTitle(title).stream()
                .map(HiringMapper::toDetailDTO)
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
    public HiringDetailDTO searchQuestionById(String id) {
        HiringEntity hiringEntity = hiringRepository.findQuestionEntityById(id);

        return HiringMapper.toDetailDTO(hiringEntity);
    }

    /**
     * question을 저장한다.
     * @param hiringDetailDTO
     * @return
     */
    public String saveQuestion(HiringDetailDTO hiringDetailDTO) {

        HiringEntity savedEntity = hiringRepository.save(
                HiringMapper.toEntity(hiringDetailDTO));

        return HiringMapper.toDetailDTO(savedEntity).getId();
    }


}
