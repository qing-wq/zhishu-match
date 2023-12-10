package ink.whi.project.modules.team.repo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import ink.whi.project.common.domain.dto.TeamMemberDTO;
import ink.whi.project.common.enums.TeamStatusEnum;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.modules.team.convreter.TeamConverter;
import ink.whi.project.modules.team.repo.entity.TeamDO;
import ink.whi.project.modules.team.repo.entity.TeamMemberDO;
import ink.whi.project.modules.team.repo.mapper.TeamMapper;
import ink.whi.project.modules.team.repo.mapper.TeamMemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Slf4j
@Repository
public class TeamDao extends ServiceImpl<TeamMapper, TeamDO> {

    public TeamDO queryByCompetitionIdAndCaptain(Long competitionId, Long captain) {
        return lambdaQuery().eq(TeamDO::getCompetitionId, competitionId)
                .eq(TeamDO::getCaptain, captain)
                .eq(TeamDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
    }

    public TeamDO queryByTeamName(Long competitionId, String name) {
        return lambdaQuery().eq(TeamDO::getName, name)
                .eq(TeamDO::getCompetitionId, competitionId)
                .eq(TeamDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
    }

    /**
     * 获取用户所在的队伍
     *
     * @param competition
     * @param userId
     * @return
     */
    public Long getUserTeam(Long competition, Long userId) {
        return baseMapper.selectUserTeam(competition, userId);
    }
}
