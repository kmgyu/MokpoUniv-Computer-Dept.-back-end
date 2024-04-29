package org.mokpouniv.computerDept_backend.login;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
//    findOneWithAuthoritiesByUsername
//    @Query("{username : ?0}")
//    UserEntity findOneWithAuthoritiesByUsername(String username);

    @Query("{username : ?0}")
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String username);
}
