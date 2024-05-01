package org.mokpouniv.computerDept_backend.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    // 아이디
    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 50)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 실명
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    
    // 학번
    @NotNull
    @Size(min=4, max=8)
    private String studentNumber;

    // 전공
    @NotNull
    @Size(min = 3, max = 50)
    private String major;

    // 재학정보
    @NotNull
    @Size(min = 3, max = 50)
    private String enrollStatus;
}
