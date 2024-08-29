package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.dto.*;
import com.example.demo.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="User")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest dto) {
        return ResponseEntity.ok(userService.loginUser(dto));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserSignUpRequest dto) {
        userService.signUpUser(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/idcheck")
    public ResponseEntity<UserIdcheckResponse> checkId(
          @Valid @RequestBody UserIdcheckRequest dto) {

        UserIdcheckRequest request = new UserIdcheckRequest();
        request.setId(dto.getId());

        boolean exists = userService.idExist(request);

        UserIdcheckResponse response = new UserIdcheckResponse();
        response.setExist(exists);

        return ResponseEntity.ok(response);
    }
}
