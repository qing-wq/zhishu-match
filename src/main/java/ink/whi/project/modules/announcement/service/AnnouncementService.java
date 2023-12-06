package ink.whi.project.modules.announcement.service;

import ink.whi.project.common.domain.dto.AnnouncementDTO;
import ink.whi.project.common.domain.dto.SimpleAnnouncementDTO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.AnnouncementReq;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
public interface AnnouncementService {
    Long post(AnnouncementReq req);

    PageVo<SimpleAnnouncementDTO> list(PageParam pageParam);

    String generateSummary(String content);

    AnnouncementDTO detail(Long id);
}
