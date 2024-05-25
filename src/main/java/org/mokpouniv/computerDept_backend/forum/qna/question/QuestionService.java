package org.mokpouniv.computerDept_backend.forum.qna.question;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.qna.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
//    private final CommentRepository commentRepository;

    /**
     * question을 제목을 기준으로 검색한다. 문자열이 포함되어 있다면 모두 반환한다.
     * @param title
     * @return
     */
    public List<QuestionDetailDTO> searchQuestionByTitle(String title) {
        return questionRepository.findQuestionEntitiesByTitle(title).stream()
                .map(QuestionMapper::toDetailDTO)
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
    public QuestionDetailDTO searchQuestionById(String id) {
        QuestionEntity questionEntity = questionRepository.findQuestionEntityById(id);

        return QuestionMapper.toDetailDTO(questionEntity);
    }

    /**
     * question을 저장한다.
     * @param questionDetailDTO
     * @return
     */
    public String saveQuestion(QuestionDetailDTO questionDetailDTO) {

        QuestionEntity savedEntity = questionRepository.save(
                QuestionMapper.toEntity(questionDetailDTO));

        return QuestionMapper.toDetailDTO(savedEntity).getId();
    }


}
