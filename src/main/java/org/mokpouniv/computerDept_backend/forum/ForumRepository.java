package org.mokpouniv.computerDept_backend.forum;

import org.mokpouniv.computerDept_backend.forum.notice.NoticeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends MongoRepository<NoticeEntity, String> {

    @Query(value = "{author:'?0'}",fields="{'content':1}")
    List<NoticeEntity> findAllNoticeEntityBy(String author);

    @Query("{_id: ?0}")
    NoticeEntity findNoticeEntityById(String id);

    @Query("{title:'?0'}")
    List<NoticeEntity> findAllNoticeEntityByTitle(String title); // 여러 결과를 반환
}