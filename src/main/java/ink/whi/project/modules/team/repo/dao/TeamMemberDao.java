package ink.whi.project.modules.team.repo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import ink.whi.project.common.domain.dto.TeamMemberDTO;
import ink.whi.project.common.enums.TeamStatusEnum;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.modules.team.convreter.TeamConverter;
import ink.whi.project.modules.team.repo.entity.TeamMemberDO;
import ink.whi.project.modules.team.repo.mapper.TeamMapper;
import ink.whi.project.modules.team.repo.mapper.TeamMemberMapper;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: qing
 * @Date: 2023/12/11
 */
@Repository
public class TeamMemberDao extends ServiceImpl<TeamMemberMapper, TeamMemberDO> {


    public void join(Long teamId, Long userId) {
        TeamMemberDO record = lambdaQuery().eq(TeamMemberDO::getTeamId, teamId)
                .eq(TeamMemberDO::getUserId, userId)
                .one();
        if (record != null) {
            TeamStatusEnum status = TeamStatusEnum.formCode(record.getStatus());
            switch (status) {
                case WAIT -> throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "您已提交已申请");
                case JOINED -> throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "您已加入该队伍");
                case NOT_JOIN -> {
                    record.setStatus(TeamStatusEnum.WAIT.getCode());
                    updateById(record);
                }
            }
        } else {
            record = new TeamMemberDO();
            record.setUserId(userId);
            record.setStatus(TeamStatusEnum.WAIT.getCode());
            save(record);
        }
    }

    public void agree(Long teamId, Long member) {
        TeamMemberDO record = lambdaQuery().eq(TeamMemberDO::getTeamId, teamId)
                .eq(TeamMemberDO::getUserId, member)
                .one();
        if (Objects.equals(record.getStatus(), TeamStatusEnum.WAIT.getCode())) {
            record.setStatus(TeamStatusEnum.JOINED.getCode());
            updateById(record);
        } else {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE);
        }
    }

    public Integer getMemberCount(Long teamId) {
        return lambdaQuery().eq(TeamMemberDO::getTeamId, teamId)
                .eq(TeamMemberDO::getStatus, TeamStatusEnum.JOINED.getCode())
                .count().intValue();
    }

    public List<TeamMemberDTO> listTeamMember(Long teamId) {
        List<TeamMemberDO> list = lambdaQuery().eq(TeamMemberDO::getTeamId, teamId)
                .in(TeamMemberDO::getStatus, TeamStatusEnum.JOINED.getCode(), TeamStatusEnum.WAIT.getCode())
                .list();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return TeamConverter.toDtoList(list);
    }
}
