package org.mokpouniv.computerDept_backend.forum.qna.comment;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.qna.answer.AnswerEntity;
import org.mokpouniv.computerDept_backend.forum.qna.question.QuestionEntity;
import org.mokpouniv.computerDept_backend.forum.qna.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {


    private final QuestionRepository questionRepository;

    public String saveQuestionComment(CommentDTO commentDTO, String questionId) {
//        try catch 문 넣을 것
        QuestionEntity questionEntity = questionRepository.findQuestionEntityById(questionId);
        List<CommentEntity> updatedComment = questionEntity.getCommentEntityList();
        updatedComment.add(CommentMapper.toEntity(commentDTO));
        questionEntity.setCommentEntityList(updatedComment);
        questionRepository.save(questionEntity);
        return questionId;
    }

    public String saveAnswerComment(CommentDTO commentDTO, String questionId, String answerId) {
        QuestionEntity questionEntity = questionRepository.findQuestionEntityById(questionId);
        Optional<AnswerEntity> answerEntityList = questionEntity.getAnswerEntityList().stream()
                .filter(item -> item.getId().equals(answerId)).findFirst();

        answerEntityList.ifPresent(item -> item.getCommentEntityList().add(CommentMapper.toEntity(commentDTO)));
        if (answerEntityList.isEmpty()) {
            return "save failed";
        } else {
            return "save success!!";
        }
    }


//    public List<CommentDTO> findCommentByQuestionId(String questionId) {
//        List<CommentEntity> resultEntites = commentRepository.findByQuestionId(questionId);
//
//        return resultEntites.stream()
//                .map(CommentMapper::toDTO)
//                .collect(Collectors.toList());
//
//    }
//
//    public CommentDTO saveComment(CommentDTO commentDTO, String questionId) {
//        QuestionEntity questionEntity = questionRepository.findQuestionEntityById(questionId);
//        CommentEntity commentEntity = commentRepository.save(CommentMapper.toEntity(commentDTO, questionEntity));
//        return CommentMapper.toDTO(commentEntity);
//    }
}
