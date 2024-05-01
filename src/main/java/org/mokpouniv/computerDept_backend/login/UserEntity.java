package org.mokpouniv.computerDept_backend.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(value = {"userid", "studentNumber", "password", "activated"})
public class UserEntity {
//    @Id
//    private String PK;
//    로그인 아이디
    @Id
    private String username;

//    사용자 이름
    private String name;

//    학번
    private String studentNumber;

    private String password;

//    전공
    private String major;


    private boolean activated;

//    재학 정보
    private String enrollStatus;

//    @JoinTable(
//            name = "user_authority",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    @DBRef()
    private Set<AuthorityEntity> authorities;
}
