package ink.whi.project.controller;

import ink.whi.project.exception.StatusEnum;
import ink.whi.project.filter.GlobalInitHelper;
import ink.whi.project.service.user.service.UserService;
import ink.whi.project.utils.JwtUtil;
import ink.whi.project.utils.SessionUtil;
import ink.whi.project.vo.dto.BaseUserDTO;
import ink.whi.project.vo.res.ResVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: qing
 * @Date: 2023/10/17
 */
@RestController
public class LoginRestController {

    @Autowired
    private UserService userService;

    /**
     * 账号密码登录
     * @param request
     * @param response
     * @return
     */
    @PostMapping(path = {"/", ""})
    public ResVo<BaseUserDTO> login(HttpServletRequest request,
                                    HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "用户名或密码不能为空");
        }
        BaseUserDTO info = userService.passwordLogin(username, password);
        // 签发token
        String token = JwtUtil.createToken(info.getUserId());
        if (StringUtils.isNotBlank(token)) {
            response.addCookie(SessionUtil.newCookie(GlobalInitHelper.SESSION_KEY, token));
            return ResVo.ok(info);
        } else {
            return ResVo.fail(StatusEnum.LOGIN_FAILED_MIXED, "登录失败，请重试");
        }
    }

}
