package org.mokpouniv.computerDept_backend.forum.qna;

import lombok.RequiredArgsConstructor;
import org.mokpouniv.computerDept_backend.forum.comment.CommentMapper;
import org.mokpouniv.computerDept_backend.forum.comment.CommentRepository;
import org.mokpouniv.computerDept_backend.forum.notice.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;

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
        QuestionEntity questionEntity = questionRepository.findQuestionEntityById(id);

        QuestionDetailDTO questionDetailDTO = QuestionMapper.toDetailDTO(questionEntity);

        questionDetailDTO.setCommentDTOList(
                commentRepository.findByQuestionId(id).stream()
                        .map(CommentMapper::toDTO)
                        .collect(Collectors.toList())
                );

        return questionDetailDTO;
    }

    public String saveQuestion(QuestionDetailDTO questionDetailDTO) {

        QuestionEntity savedEntity = questionRepository.save(
                QuestionMapper.toEntity(questionDetailDTO));

        return QuestionMapper.toDetailDTO(savedEntity).getId();
    }


}
