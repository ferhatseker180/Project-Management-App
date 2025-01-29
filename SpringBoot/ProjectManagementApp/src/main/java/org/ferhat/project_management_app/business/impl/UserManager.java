package org.ferhat.project_management_app.business.impl;

import org.ferhat.project_management_app.business.abstracts.IUserService;
import org.ferhat.project_management_app.core.config.modelMapper.IModelMapperService;
import org.ferhat.project_management_app.core.exceptions.NotFoundException;
import org.ferhat.project_management_app.core.exceptions.UnauthorizedException;
import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.core.utils.user.UserMessage;
import org.ferhat.project_management_app.core.utils.user.UserResultHelper;
import org.ferhat.project_management_app.dto.request.user.UserLoginRequest;
import org.ferhat.project_management_app.dto.request.user.UserSaveRequest;
import org.ferhat.project_management_app.dto.response.user.UserResponse;
import org.ferhat.project_management_app.entities.User;
import org.ferhat.project_management_app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManager implements IUserService {
    private final UserRepository userRepository;
    private final IModelMapperService modelMapperService;
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    public UserManager(UserRepository userRepository, IModelMapperService modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    @Transactional
    public ResultData<UserResponse> save(UserSaveRequest userSaveRequest) {
        // Email kontrolü
        if (userRepository.findByEmail(userSaveRequest.getEmail()) != null) {
            throw new RuntimeException(UserMessage.USER_EXISTS);
        }
        User user = modelMapperService.forRequest().map(userSaveRequest, User.class);
        User savedUser = userRepository.save(user);
        UserResponse userResponse = modelMapperService.forResponse().map(savedUser, UserResponse.class);
        return UserResultHelper.created(userResponse);
    }

    @Override
    public UserResponse getByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException(UserMessage.USER_NOT_FOUND);
        }
        return modelMapperService.forResponse().map(user, UserResponse.class);
    }

    @Override
    public UserResponse login(UserLoginRequest userLoginRequest) {
        logger.info("Login request received for email: {}", userLoginRequest.getEmail());

        User user = userRepository.findByEmail(userLoginRequest.getEmail());

        if (user == null) {
            logger.warn("FAILED LOGIN: Email '{}' not found", userLoginRequest.getEmail());
            throw new NotFoundException("User not found with email: " + userLoginRequest.getEmail());
        }

        if (!user.getPassword().equals(userLoginRequest.getPassword())) {
            logger.warn("FAILED LOGIN: Incorrect password for email '{}'", userLoginRequest.getEmail());
            throw new UnauthorizedException("Invalid email or password");
        }

        logger.info("SUCCESSFUL LOGIN: Email '{}'", userLoginRequest.getEmail());
        return modelMapperService.forResponse().map(user, UserResponse.class);
    }
}
