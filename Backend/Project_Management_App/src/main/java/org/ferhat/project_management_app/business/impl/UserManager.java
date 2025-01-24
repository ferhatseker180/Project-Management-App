package org.ferhat.project_management_app.business.impl;

import org.ferhat.project_management_app.business.abstracts.IUserService;
import org.ferhat.project_management_app.core.config.modelMapper.IModelMapperService;
import org.ferhat.project_management_app.core.exceptions.NotFoundException;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.user.UserMessage;
import org.ferhat.project_management_app.core.utils.user.UserResultHelper;
import org.ferhat.project_management_app.dto.request.UserRequest;
import org.ferhat.project_management_app.dto.response.UserResponse;
import org.ferhat.project_management_app.entities.Users;
import org.ferhat.project_management_app.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserManager implements IUserService {

    private final UserRepository userRepository;
    private final IModelMapperService modelMapperService;

    public UserManager(UserRepository userRepository, IModelMapperService modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public ResultData<UserResponse> save(UserRequest userRequest) {
        // Email kontrolü
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new RuntimeException(UserMessage.USER_EXISTS);
        }

        Users user = modelMapperService.forRequest().map(userRequest, Users.class);
        Users savedUser = userRepository.save(user);
        UserResponse userResponse = modelMapperService.forResponse().map(savedUser, UserResponse.class);
        return UserResultHelper.created(userResponse);
    }

    @Override
    public ResultData<UserResponse> getByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException(UserMessage.USER_NOT_FOUND);
        }
        UserResponse userResponse = modelMapperService.forResponse().map(user, UserResponse.class);
        return UserResultHelper.success(userResponse);
    }


}




