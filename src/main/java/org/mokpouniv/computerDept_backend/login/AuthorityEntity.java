package org.mokpouniv.computerDept_backend.login;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityEntity {

    @Id
    private String authorityName;
}
