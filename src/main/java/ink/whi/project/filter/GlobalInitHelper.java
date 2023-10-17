package ink.whi.project.filter;

import ink.whi.project.context.ReqInfoContext;
import ink.whi.project.exception.BusinessException;
import ink.whi.project.exception.StatusEnum;
import ink.whi.project.service.user.service.UserService;
import ink.whi.project.utils.JwtUtil;
import ink.whi.project.vo.dto.BaseUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: qing
 * @Date: 2023/4/27
 */
@Slf4j
@Component
public class GlobalInitHelper {
    private static final String SESSION_KEY = "sai-session";

    @Autowired
    private UserService userService;

    /**
     * 初始化用户信息
     * @param reqInfo
     */
    public void initUserInfo(ReqInfoContext.ReqInfo reqInfo) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        if (request.getCookies() == null) {
            return;
        }
        for (Cookie cookie : request.getCookies()) {
            if (SESSION_KEY.equalsIgnoreCase(cookie.getName())) {
                BaseUserDTO user = VerifyToken(cookie.getValue(), response);
                if (user != null) {
                    reqInfo.setUserId(user.getUserId());
                    reqInfo.setUser(user);
                }
            }
        }
    }

    /**
     * 校验token
     *
     * @param token
     * @param response
     */
    private BaseUserDTO VerifyToken(String token, HttpServletResponse response) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Long userId = JwtUtil.isVerify(token);
        BaseUserDTO user = userService.queryBasicUserInfo(userId);
        if (user == null) {
            throw BusinessException.newInstance(StatusEnum.JWT_VERIFY_EXISTS);
        }

        // 检查token是否需要更新
        if (JwtUtil.isNeedUpdate(token)) {
            token = JwtUtil.createToken(userId);
            response.addCookie(new Cookie(SESSION_KEY, token));
        }
        return user;
    }
}
