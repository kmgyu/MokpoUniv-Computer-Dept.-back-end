package org.mokpouniv.computerDept_backend.login;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Setter
@Getter
class LoginRequest {
    private String userId;
    private String userPw;
}

@Setter
@Getter
class LoginResponse {
    private boolean success;
    private String message;
}

@RestController
public class LoginController {

    @PostMapping("/login") // POST 요청 처리
    private ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();

        if ("admin".equals(request.getUserId()) && "password123".equals(request.getUserPw())) {
            response.setMessage("Login successful!");
            return ResponseEntity.ok(response);
        } else {
            response.setMessage("Invalid user ID or password.");
            return ResponseEntity.status(401).body(response);
        }
    }
}
