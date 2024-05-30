package org.mokpouniv.computerDept_backend.forum.photo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends MongoRepository<PhotoEntity, String> {

}
