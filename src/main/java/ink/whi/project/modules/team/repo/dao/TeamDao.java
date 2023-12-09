package ink.whi.project.modules.team.repo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.ChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
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

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    public TeamDO queryByCompetitionIdAndCaptain(Long competitionId, Long captain) {
        return lambdaQuery().eq(TeamDO::getCompetitionId, captain)
                .eq(TeamDO::getCaptain, captain)
                .eq(TeamDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
    }

    public TeamDO queryByTeamName(String name) {
        return lambdaQuery().eq(TeamDO::getName, name)
                .eq(TeamDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
    }

    public void join(TeamMemberDO teamMember) {
        teamMemberMapper.insert(teamMember);
    }

    public void agree(Long teamId, Long member) {
        LambdaQueryWrapper<TeamMemberDO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(TeamMemberDO::getTeamId, teamId)
                .eq(TeamMemberDO::getUserId, member);
        TeamMemberDO record = teamMemberMapper.selectOne(wrapper);
        if (Objects.equals(record.getStatus(), TeamStatusEnum.WAIT.getCode())) {
            record.setStatus(TeamStatusEnum.JOINED.getCode());
            teamMemberMapper.updateById(record);
        } else {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE);
        }
    }

    public Integer getMemberCount(Long teamId) {
        LambdaQueryChainWrapper<TeamMemberDO> chainWrapper = ChainWrappers.lambdaQueryChain(teamMemberMapper);
        return chainWrapper.eq(TeamMemberDO::getTeamId, teamId)
               .eq(TeamMemberDO::getStatus, TeamStatusEnum.JOINED.getCode())
               .count().intValue();
    }

    public List<TeamMemberDTO> listTeamMember(Long teamId) {
        LambdaQueryChainWrapper<TeamMemberDO> chainWrapper = ChainWrappers.lambdaQueryChain(teamMemberMapper);
        List<TeamMemberDO> list = chainWrapper.eq(TeamMemberDO::getTeamId, teamId)
                .in(TeamMemberDO::getStatus, TeamStatusEnum.JOINED.getCode(), TeamStatusEnum.WAIT.getCode())
                .list();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return TeamConverter.toDtoList(list);
    }
}