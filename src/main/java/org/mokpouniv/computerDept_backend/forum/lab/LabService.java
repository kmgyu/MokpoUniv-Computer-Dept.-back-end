package org.mokpouniv.computerDept_backend.forum.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabService {

    private final LabRepository labRepository;
//    private final CommentRepository commentRepository;

    /**
     * question을 제목을 기준으로 검색한다. 문자열이 포함되어 있다면 모두 반환한다.
     * @param title
     * @return
     */
    public List<LabDetailDTO> searchQuestionByTitle(String title) {
        return labRepository.findQuestionEntitiesByTitle(title).stream()
                .map(LabMapper::toDetailDTO)
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
    public LabDetailDTO searchQuestionById(String id) {
        LabEntity labEntity = labRepository.findQuestionEntityById(id);

        return LabMapper.toDetailDTO(labEntity);
    }

    /**
     * question을 저장한다.
     * @param labDetailDTO
     * @return
     */
    public String saveQuestion(LabDetailDTO labDetailDTO) {

        LabEntity savedEntity = labRepository.save(
                LabMapper.toEntity(labDetailDTO));

        return LabMapper.toDetailDTO(savedEntity).getId();
    }


}
