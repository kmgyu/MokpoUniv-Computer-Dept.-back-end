package org.mokpouniv.computerDept_backend.forum.notice;

import org.mokpouniv.computerDept_backend.forum.notice.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends MongoRepository<NoticeEntity, String> {
    /**
     * 작성자를 기준으로 NoticeEntity 검색, List 반환
     * @param author
     * @return
     */
    @Query(value = "{author:'?0'}")
    Page<NoticeEntity> findAllNoticeEntityByAuthor(Pageable pageable, String author);

    /**
     * 작성글의 id를 기준으로 NoticeEntity 검색. id는 식별자이므로 하나만 반환함.
     * @param id
     * @return
     */
    @Query("{_id: ?0}")
    NoticeEntity findNoticeEntityById(String id);

    /**
     * 제목에 문자열 포함하는 NoticeEntity를 List로 모두 반환
     * @param title
     * @return
     */
    @Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }")
    Page<NoticeEntity> findAllNoticeEntityByTitle(Pageable pageable, String title); // 여러 결과를 반환

}