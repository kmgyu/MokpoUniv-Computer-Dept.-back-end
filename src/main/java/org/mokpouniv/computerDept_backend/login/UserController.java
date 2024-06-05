package org.mokpouniv.computerDept_backend.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "User Controller")
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 시 중복검사
     * @param username
     * @return
     */
    @ApiOperation(value = "Duplicate Check for Sign Up")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Map.class),
            @ApiResponse(code = 400, message = "Failure")
    })
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

    /**
     * 회원가입
     * @param userDTO
     * @return
     */
    @ApiOperation(value = "Sign Up")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserEntity.class),
            @ApiResponse(code = 400, message = "Failure")
    })
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(
            @Valid @RequestBody UserDTO userDTO
    ) {
        return ResponseEntity.ok(userService.signup(userDTO));
    }

    /**
     * 정상적인 회원 처리에 대한 user entity 반환 (테스트용)
     * @return
     */
    @ApiOperation(value = "Get User Information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserEntity.class),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserEntity> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    /**
     * admin일 경우에 대한 테스트용
     * @param username
     * @return
     */
    @ApiOperation(value = "Get User Information (Admin)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UserEntity.class),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserEntity> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }
}
