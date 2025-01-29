package org.ferhat.project_management_app.controller;

import jakarta.validation.Valid;
import org.ferhat.project_management_app.business.abstracts.IUserService;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.user.UserResultHelper;
import org.ferhat.project_management_app.dto.request.user.UserLoginRequest;
import org.ferhat.project_management_app.dto.request.user.UserSaveRequest;
import org.ferhat.project_management_app.dto.response.user.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = "http://localhost:5173") // Frontend'in adresini buraya ekle
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<UserResponse> save(@Valid @RequestBody UserSaveRequest userSaveRequest) {
        return userService.save(userSaveRequest);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResultData<UserResponse>> getByEmail(@PathVariable String email) {
        ResultData<UserResponse> result = UserResultHelper.success(userService.getByEmail(email));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ResultData<UserResponse>> login(@Valid @RequestBody UserLoginRequest loginRequest) {
        UserResponse userResponse = userService.login(loginRequest);
        return ResponseEntity.ok(UserResultHelper.successfulLogin(userResponse));
    }

}
