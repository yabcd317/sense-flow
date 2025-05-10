package com.sense.controller;

import com.sense.constant.JwtClaimsConstant;
import com.sense.dto.LoginDTO;
import com.sense.entity.User;
import com.sense.properties.JwtProperties;
import com.sense.result.Result;
import com.sense.service.UserService;
import com.sense.utils.JwtUtil;
import com.sense.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/sense")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public Result<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        log.info("用户登录：{}", loginDTO);

        User user = userService.login(loginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        LoginVO loginVO = LoginVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .build();

        return Result.success(loginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

}
