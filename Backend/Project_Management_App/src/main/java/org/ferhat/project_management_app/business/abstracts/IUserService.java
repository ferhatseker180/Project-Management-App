package org.ferhat.project_management_app.business.abstracts;

import org.ferhat.project_management_app.core.result.ResultData;
import org.ferhat.project_management_app.dto.request.UserRequest;
import org.ferhat.project_management_app.dto.response.UserResponse;

public interface IUserService {
    ResultData<UserResponse> save(UserRequest userRequest);
    ResultData<UserResponse> getByEmail(String email);
}
