package ink.whi.project.modules.announcement.service.Impl;

import ink.whi.project.common.domain.dto.AnnouncementDTO;
import ink.whi.project.common.domain.dto.SimpleAnnouncementDTO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.AnnouncementReq;
import ink.whi.project.common.enums.PushStatusEnum;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.common.utils.AnnouncementUtil;
import ink.whi.project.modules.announcement.converter.AnnouncementConverter;
import ink.whi.project.modules.announcement.repo.dao.AnnouncementDao;
import ink.whi.project.modules.announcement.repo.entity.Announcement;
import ink.whi.project.modules.announcement.service.AnnouncementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public Long post(AnnouncementReq req) {
        Announcement anno = AnnouncementConverter.toDo(req);
        if (req.getAnnouncementId() == null) {
            return insertAnno(anno);
        } else {
            // 更新公告
            Announcement record = announcementDao.getById(req.getAnnouncementId());
            if (record == null) {
                throw BusinessException.newInstance(StatusEnum.RECORDS_NOT_EXISTS, "记录不存在：" + anno.getId());
            }
            updateAnno(anno);
            return anno.getId();
        }
    }

    private Long insertAnno(Announcement anno) {
        if (StringUtils.isBlank(anno.getSummary())) {
            String summary = AnnouncementUtil.pickSummary(anno.getContent());
            anno.setSummary(summary);
        }
        anno.setStatus(PushStatusEnum.ONLINE.getCode());
        announcementDao.save(anno);
        return anno.getId();
    }

    private void updateAnno(Announcement anno) {
        announcementDao.updateById(anno);
    }

    @Override
    public PageVo<SimpleAnnouncementDTO> list(PageParam pageParam) {
        List<Announcement> announcements = announcementDao.listAll(pageParam);
        List<SimpleAnnouncementDTO> list = AnnouncementConverter.toDtoList(announcements);
        return PageVo.build(list, pageParam.getPageSize(), pageParam.getPageNum(), announcementDao.countAll());
    }

    /**
     * 生成文章摘要
     *
     * @param content
     * @return
     */
    @Override
    public String generateSummary(String content) {
        return AnnouncementUtil.pickSummary(content);
    }

    @Override
    public AnnouncementDTO detail(Long id) {
        Announcement anno = announcementDao.getById(id);
        return AnnouncementConverter.toDto(anno);
    }
}
