package org.mokpouniv.computerDept_backend.forum;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends MongoRepository<ForumEntity, String> {


    public interface ForumEntityRepository extends MongoRepository<ForumEntity, String> {

        // 정규 표현식을 사용하여 단어를 포함하는 모든 title을 조회하는 쿼리
        @Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }")
        List<ForumEntity> findAllByTitleRegex(String keyword);
    }

    ForumEntity findAllByTitleRegex(String title);

    public long count();
}
