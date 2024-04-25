package org.mokpouniv.computerDept_backend.forum;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends MongoRepository<ForumEntity, String> {
    @Query("{title:'?0'}")
    ForumEntity findGroceryItemByName(String title);

    @Query(value = "{author:'?0'}",fields="{'content':1}")
    List<ForumEntity>findAllBy(String author);

    ForumEntity findGroceryItemByTitle(String title);

    public long count();
}
