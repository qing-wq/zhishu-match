package ink.whi.project.hook.interceptor;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author: qing
 * @Date: 2023/12/6
 */
@Order(-100)
@Slf4j
@Component
public class AuthInterceptor implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            Permission permission = handlerMethod.getMethod().getAnnotation(Permission.class);
            if (permission == null) {
                permission = handlerMethod.getBeanType().getAnnotation(Permission.class);
            }

            // ALL
            if (permission == null || permission.role() == UserRole.ALL) {
                return true;
            }

            // 登录
            if (permission.role() == UserRole.LOGIN && ReqInfoContext.getReqInfo().getUserId() == null) {
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().println(JsonUtil.toStr(ResVo.fail(StatusEnum.FORBID_ERROR_MIXED, "未登录")));
                response.getWriter().flush();
                return false;
            }

            // admin
            if (permission.role() == UserRole.ADMIN && ReqInfoContext.getReqInfo().getUser() != null &&
                    !Objects.equals(UserRole.ADMIN.name(), ReqInfoContext.getReqInfo().getUser().getRole())) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
        }
        return true;
    }
}
