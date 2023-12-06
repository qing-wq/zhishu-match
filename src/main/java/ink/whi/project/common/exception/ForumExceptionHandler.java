package ink.whi.project.common.exception;

import ink.whi.project.common.domain.vo.ResVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: qing
 * @Date: 2023/4/25 23:50
 */
@Slf4j
@RestControllerAdvice
@Order(-100)
public class ForumExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResVo<String> forumExceptionHandler(HttpServletResponse resp, Exception e) {
        BusinessException ex = (BusinessException) e;
        Status errStatus = ex.getStatus();
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        setErrorCode(errStatus, resp);
        log.error("capture ForumException: {}", errStatus.getMsg());
        return ResVo.fail(errStatus);
    }


    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResVo<String> httpMediaTypeNotAcceptableExceptionHandler(HttpServletResponse resp, Exception e) {
        Status errStatus = Status.newStatus(StatusEnum.RECORDS_NOT_EXISTS, ExceptionUtils.getStackTrace(e));
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        setErrorCode(errStatus, resp);
        log.error("capture NestedRuntimeException: {}", ExceptionUtils.getStackTrace(e));
        return ResVo.fail(errStatus);
    }

    @ExceptionHandler(NestedRuntimeException.class)
    public ResVo<String> nestedRuntimeExceptionHandler(HttpServletResponse resp, Exception e) {
        Status errStatus = Status.newStatus(StatusEnum.UNEXPECT_ERROR, e.getMessage());
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        setErrorCode(errStatus, resp);
        log.error("capture NestedRuntimeException: {}", e.getMessage());
        return ResVo.fail(errStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResVo<String> exceptionHandler(HttpServletResponse resp, Exception e) {
        Status errStatus = Status.newStatus(StatusEnum.UNEXPECT_ERROR, ExceptionUtils.getStackTrace(e));
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        setErrorCode(errStatus, resp);
        log.error("capture Exception: {}", ExceptionUtils.getStackTrace(e));
        return ResVo.fail(errStatus);
    }

    private void setErrorCode(Status status, HttpServletResponse response) {
        if (StatusEnum.is5xx(status.getCode())) {
            response.setStatus(500);
        } else if (StatusEnum.is403(status.getCode())) {
            response.setStatus(403);
        } else {
            response.setStatus(404);
        }
    }
}
