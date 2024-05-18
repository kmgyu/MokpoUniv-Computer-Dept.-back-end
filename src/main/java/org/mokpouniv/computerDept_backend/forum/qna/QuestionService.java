package org.mokpouniv.computerDept_backend.forum.qna;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.notice.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

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

    public QuestionDetailDTO searchQuestionById(String id) {
        return QuestionMapper.toDetailDTO(questionRepository.findQuestionEntityById(id));
    }

    public String saveQuestion(QuestionDetailDTO questionDetailDTO) {

        QuestionEntity savedEntity = questionRepository.save(
                QuestionMapper.toEntity(questionDetailDTO));

        return QuestionMapper.toDetailDTO(savedEntity).getId();
    }


}
