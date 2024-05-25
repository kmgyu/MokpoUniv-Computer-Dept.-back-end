package org.mokpouniv.computerDept_backend.forum.qna.answer;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.qna.QuestionRepository;
import org.mokpouniv.computerDept_backend.forum.qna.question.QuestionEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final QuestionRepository questionRepository;

    public String saveAnswer(AnswerDTO answerDTO, String questionId) {
        QuestionEntity questionEntity = questionRepository.findQuestionEntityById(questionId);
        List<AnswerEntity> updatedAnswer = questionEntity.getAnswerEntityList();

        updatedAnswer.add(AnswerMapper.toEntity(answerDTO));

        questionEntity.setAnswerEntityList(updatedAnswer);
        questionEntity.setAnswer(true);

        questionRepository.save(questionEntity);

        return questionId;
    }

}
