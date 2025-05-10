package com.sense.service;

import com.sense.dto.LoginDTO;
import com.sense.entity.User;

public interface UserService {

    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    User login(LoginDTO loginDTO);

}
