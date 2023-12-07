package ink.whi.project.modules.competition.repo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.common.domain.base.BaseDO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.enums.PushStatusEnum;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.modules.announcement.repo.entity.Announcement;
import ink.whi.project.modules.competition.converter.CompetitionConverter;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import ink.whi.project.modules.competition.repo.mapper.CompetitionMapper;
import ink.whi.project.modules.rank.repo.entity.RankDO;
import ink.whi.project.modules.rank.repo.mapper.RankMapper;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
public class CompetitionDao extends ServiceImpl<CompetitionMapper, CompetitionDO> {
    public List<CompetitionDO> listAll(PageParam pageParam) {
        return lambdaQuery()
                .eq(CompetitionDO::getDeleted, YesOrNoEnum.NO.getCode())
                .orderByDesc(BaseDO::getCreateTime)
                .last(PageParam.getLimitSql(pageParam))
                .list();
    }

    public Long countAll() {
        return lambdaQuery().eq(CompetitionDO::getDeleted, YesOrNoEnum.NO.getCode())
                .count();
    }
}
