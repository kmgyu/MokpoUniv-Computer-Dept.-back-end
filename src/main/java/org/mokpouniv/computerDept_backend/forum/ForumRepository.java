package org.mokpouniv.computerDept_backend.forum;

import org.mokpouniv.computerDept_backend.forum.notice.NoticeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends MongoRepository<NoticeEntity, String> {

    @Query(value = "{author:'?0'}",fields="{'content':1}")
    List<NoticeEntity> findAllBy(String author);

    @Query("{_id: ?0}")
    NoticeEntity findForumEntityById(String id);

    @Query("{title:'?0'}")
    List<NoticeEntity> findNoticeAllByTitle(String title); // 여러 결과를 반환
}