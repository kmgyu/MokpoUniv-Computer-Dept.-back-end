package org.mokpouniv.computerDept_backend.login;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class UserController {
    private final UserService userService;

    @PostMapping("/duplicate")
    public ResponseEntity<Map<String, Object>> duplicationVerify(
            @RequestBody String username
    ) {
        ResponseEntity.ok("message");
        Map<String, Object> response = new HashMap<>();
        if (userService.searchDuplicateUsername(username)) {
            response.put("message", "사용할 수 있는 아이디입니다.");
            response.put("verify_result", true);
        } else {
            response.put("message", "중복이 존재합니다.");
            response.put("verify_result", false);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(
            @Valid @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(userService.signup(userDTO));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserEntity> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserEntity> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}
