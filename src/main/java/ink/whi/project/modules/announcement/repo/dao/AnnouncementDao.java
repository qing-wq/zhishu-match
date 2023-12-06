package ink.whi.project.modules.announcement.repo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.common.domain.base.BaseDO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.enums.PushStatusEnum;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.modules.announcement.repo.entity.Announcement;
import ink.whi.project.modules.announcement.repo.mapper.AnnouncementMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
@Repository
public class AnnouncementDao extends ServiceImpl<AnnouncementMapper, Announcement> {
    public List<Announcement> listAll(PageParam pageParam) {
        return lambdaQuery().eq(Announcement::getStatus, PushStatusEnum.ONLINE.getCode())
                .eq(Announcement::getDeleted, YesOrNoEnum.NO.getCode())
                .orderByDesc(BaseDO::getCreateTime)
                .last(PageParam.getLimitSql(pageParam))
                .list();
    }

    public Long countAll() {
        return lambdaQuery().eq(Announcement::getStatus, PushStatusEnum.ONLINE.getCode())
                .eq(Announcement::getDeleted, YesOrNoEnum.NO.getCode())
                .count();
    }
}
