package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.domain.req.UserSaveReq;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.controller.helper.LoginHelper;
import ink.whi.project.modules.user.service.UserService;
import ink.whi.project.common.utils.JwtUtil;
import ink.whi.project.common.utils.SessionUtil;
import ink.whi.project.common.domain.dto.BaseUserDTO;
import ink.whi.project.common.domain.vo.ResVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ink.whi.project.common.utils.SessionUtil.SESSION_KEY;

/**
 * 登录接口
 * @author: qing
 * @Date: 2023/10/17
 */
@RestController
public class LoginRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginHelper loginHelper;

    /**
     * 账号密码登录
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping(path = "login")
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
            response.addCookie(SessionUtil.newCookie(SESSION_KEY, token));
            return ResVo.ok(info);
        } else {
            return ResVo.fail(StatusEnum.LOGIN_FAILED_MIXED, "登录失败，请重试");
        }
    }

    /**
     * 登出接口
     *
     * @param response
     * @return
     */
    @GetMapping(path = "logout")
    @Permission(role = UserRole.LOGIN)
    public ResVo<String> logout(HttpServletResponse response) {
        response.addCookie(SessionUtil.delCookie(SESSION_KEY));
        return ResVo.ok("ok");
    }

    /**
     * 用户注册
     *
     * @param req
     * @return
     */
    @PostMapping(path = "register")
    public ResVo<Long> register(@Validated @RequestBody UserSaveReq req, HttpServletResponse response) {
        if (StringUtils.isBlank(req.getUsername()) || StringUtils.isBlank(req.getPassword())) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "账号密码不能为空");
        }

        // 邮箱校验
        loginHelper.verifyEmail(req.getUsername(), req.getCode());

        Long userId = userService.saveUser(req);
        // 签发token
        String token = JwtUtil.createToken(userId);
        if (StringUtils.isBlank(token)) {
            return ResVo.fail(StatusEnum.TOKEN_NOT_EXISTS);
        }
        response.addCookie(SessionUtil.newCookie(SESSION_KEY, token));
        return ResVo.ok(userId);
    }

    /**
     * 发送验证码
     *
     * @param email
     * @return
     */
    @PostMapping(path = "code")
    public ResVo<String> code(@RequestParam("email") String email) {
        String code = loginHelper.subscribe(email);
        return ResVo.ok(code);
    }
}