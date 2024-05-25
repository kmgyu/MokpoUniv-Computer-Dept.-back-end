package org.mokpouniv.computerDept_backend.forum.qna;

import org.mokpouniv.computerDept_backend.forum.qna.question.QuestionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, String> {

    @Query("{_id : ?0}")
    public QuestionEntity findQuestionEntityById(String id);

    @Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }")
    public List<QuestionEntity> findQuestionEntitiesByTitle(String title);
}
