package org.mokpouniv.computerDept_backend.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/**
 * UserController 에 대한 service
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Boolean searchDuplicateUsername(String username) {
        return userRepository.findUserEntityByUsername(username).isEmpty();
    }

    @Transactional
    public UserEntity signup(UserDTO userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        // 가입되어 있지 않은 회원이면,
        // 권한 정보 만들고
        AuthorityEntity authority = AuthorityEntity.builder()
                .authorityName("ROLE_USER")
                .build();

        // 유저 정보를 만들어서 save
        UserEntity user = UserEntity.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .major(userDto.getMajor())
                .enrollStatus(userDto.getEnrollStatus())
                .studentNumber(userDto.getStudentNumber())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    // 유저,권한 정보를 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<UserEntity> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
