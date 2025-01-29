package org.ferhat.project_management_app.business.abstracts;

import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.user.UserLoginRequest;
import org.ferhat.project_management_app.dto.request.user.UserSaveRequest;
import org.ferhat.project_management_app.dto.response.user.UserResponse;

public interface IUserService {

    ResultData<UserResponse> save(UserSaveRequest userSaveRequest);

    UserResponse getByEmail(String email);

    UserResponse login(UserLoginRequest userLoginRequest);
}
