package org.mokpouniv.computerDept_backend.forum.comment;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.qna.QuestionEntity;
import org.mokpouniv.computerDept_backend.forum.qna.QuestionMapper;
import org.mokpouniv.computerDept_backend.forum.qna.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final QuestionRepository questionRepository;

    public List<CommentDTO> findCommentByQuestionId(String questionId) {
        List<CommentEntity> resultEntites = commentRepository.findByQuestionId(questionId);

        return resultEntites.stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());

    }

    public CommentDTO saveComment(CommentDTO commentDTO, String questionId) {
        QuestionEntity questionEntity = questionRepository.findQuestionEntityById(questionId);
        CommentEntity commentEntity = commentRepository.save(CommentMapper.toEntity(commentDTO, questionEntity));
        return CommentMapper.toDTO(commentEntity);
    }
}
