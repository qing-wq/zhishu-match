package ink.whi.project.controller.base;

import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;

public class BaseRestController {

    public PageParam buildPageParam(Long page, Long size) {
        if (page == null) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "page=" + page);
        }
        if (page <= 0) {
            page = PageParam.DEFAULT_PAGE_NUM;
        }
        if (size == null || size > PageParam.DEFAULT_PAGE_SIZE) {
            size = PageParam.DEFAULT_PAGE_SIZE;
        }
        return PageParam.newPageInstance(page, size);
    }
}
