package com.sense.service.impl;

import com.sense.constant.MessageConstant;
import com.sense.dto.LoginDTO;
import com.sense.entity.User;
import com.sense.exception.AccountNotFoundException;
import com.sense.exception.PasswordErrorException;
import com.sense.mapper.UserMapper;
import com.sense.service.UserService;
import org.apache.commons.codec.cli.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    public User login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }


        //3、返回实体对象
        return user;
    }

}
