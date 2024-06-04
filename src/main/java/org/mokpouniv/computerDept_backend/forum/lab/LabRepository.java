package org.mokpouniv.computerDept_backend.forum.lab;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends MongoRepository<LabEntity, String> {

    /**
     * question id를 기준으로 검색후, Entity를 리턴
     * @param id
     * @return
     */
    @Query("{_id : ?0}")
    public LabEntity findQuestionEntityById(String id);

    /**
     * title이 포함된 entity를 모두 리턴
     * @param title
     * @return
     */
    @Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }")
    public List<LabEntity> findQuestionEntitiesByTitle(String title);
}
