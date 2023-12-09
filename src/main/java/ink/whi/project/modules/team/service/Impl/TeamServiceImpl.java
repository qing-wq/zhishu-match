package ink.whi.project.modules.team.service.Impl;

import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.TeamInfoDTO;
import ink.whi.project.common.domain.dto.TeamMemberDTO;
import ink.whi.project.common.domain.req.TeamSaveReq;
import ink.whi.project.common.enums.TeamStatusEnum;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.modules.competition.service.CompetitionService;
import ink.whi.project.modules.team.convreter.TeamConverter;
import ink.whi.project.modules.team.repo.dao.TeamDao;
import ink.whi.project.modules.team.repo.entity.TeamDO;
import ink.whi.project.modules.team.repo.entity.TeamMemberDO;
import ink.whi.project.modules.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private CompetitionService competitionService;

    @Override
    public Long createTeam(TeamSaveReq req) {
        TeamDO record = teamDao.queryByTeamName(req.getName());
        if (record != null) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "队伍已存在");
        } else {
            record = TeamConverter.toDo(req);
            teamDao.save(record);
            return record.getId();
        }
    }

    @Override
    public void join(Long teamId, Long userId) {
        TeamDO team = teamDao.getById(teamId);
        if (team == null) {
            throw BusinessException.newInstance(StatusEnum.RECORDS_NOT_EXISTS, "队伍不存在: " + teamId);
        }

        if (team.getCaptain().equals(userId)) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "队长不能加入队伍");
        }

        // 判断人数
        Integer maxMemberCount = competitionService.getMaxMemberCount(team.getCompetitionId());
        Integer memberCount = teamDao.getMemberCount(teamId);
        if (maxMemberCount <= memberCount) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "队伍人数已满");
        }

        TeamMemberDO teamMember = new TeamMemberDO();
        teamMember.setTeamId(teamId);
        teamMember.setUserId(userId);
        teamMember.setStatus(TeamStatusEnum.WAIT.getCode());
        teamDao.join(teamMember);
    }

    @Override
    public void agree(Long teamId, Long member) {
        Long userId = ReqInfoContext.getReqInfo().getUserId();
        TeamDO team = teamDao.getById(teamId);
        if (team == null || !Objects.equals(team.getCaptain(), userId)) {
            throw BusinessException.newInstance(StatusEnum.FORBID_ERROR);
        }
        teamDao.agree(teamId, member);
    }

    @Override
    public TeamInfoDTO queryTeamInfo(Long competitionId, Long userId) {
        TeamDO team = teamDao.queryByCompetitionIdAndCaptain(competitionId, userId);
        if (team == null) {
            // 还没组队
            return null;
        }

        return buildTeamDetailInfo(team);
    }

    private TeamInfoDTO buildTeamDetailInfo(TeamDO team) {
        TeamInfoDTO dto = new TeamInfoDTO();
        // 基本信息
        dto.setTeamId(team.getId());
        dto.setCompetitionId(team.getCompetitionId());
        dto.setName(team.getName());

        // 成员信息
        List<TeamMemberDTO> members = teamDao.listTeamMember(team.getId());
        dto.setMembers(members);
        dto.setMemberCount(members.size() + 1);
        return dto;
    }
}
