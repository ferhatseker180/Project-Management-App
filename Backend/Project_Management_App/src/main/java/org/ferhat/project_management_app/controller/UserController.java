package org.ferhat.project_management_app.controller;

import jakarta.validation.Valid;
import org.ferhat.project_management_app.business.abstracts.IUserService;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.UserRequest;
import org.ferhat.project_management_app.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<UserResponse> save(@Valid @RequestBody UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResultData<UserResponse>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

}


