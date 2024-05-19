package org.mokpouniv.computerDept_backend.forum.comment;

import org.mokpouniv.computerDept_backend.forum.qna.QuestionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {

    @Query("{ 'questionEntity' : { $ref: 'question', $id: ?0 } }")
    List<CommentEntity> findByQuestionId(String questionId);

}
