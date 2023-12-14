package ink.whi.project.modules.team.service.Impl;

import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.TeamInfoDTO;
import ink.whi.project.common.domain.dto.TeamMemberDTO;
import ink.whi.project.common.domain.req.TeamSaveReq;
import ink.whi.project.common.enums.GroupStatusEnum;
import ink.whi.project.common.enums.TeamStatusEnum;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.modules.competition.service.CompetitionService;
import ink.whi.project.modules.team.convreter.TeamConverter;
import ink.whi.project.modules.team.repo.dao.TeamDao;
import ink.whi.project.modules.team.repo.dao.TeamMemberDao;
import ink.whi.project.modules.team.repo.entity.TeamDO;
import ink.whi.project.modules.team.repo.entity.TeamMemberDO;
import ink.whi.project.modules.team.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Slf4j
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private TeamMemberDao teamMemberDao;

    @Autowired
    private CompetitionService competitionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTeam(TeamSaveReq req) {
        Long userId = req.getCaptain();
        Long competitionId = req.getCompetitionId();

        checkGrouped(competitionId, userId);

        TeamDO record = teamDao.queryByTeamName(competitionId, req.getName());
        if (record != null) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "队伍已存在");
        } else {
            record = TeamConverter.toDo(req);
            teamDao.save(record);

            // 队长存入team_member
            TeamMemberDO tm = new TeamMemberDO();
            tm.setUserId(userId);
            tm.setTeamId(record.getId());
            tm.setStatus(TeamStatusEnum.JOINED.getCode());
            teamMemberDao.save(tm);

            // 组队状态更新
            competitionService.updateGroupStatus(competitionId, userId, GroupStatusEnum.GROUP);
        }
        return record.getId();
    }

    @Override
    public void join(Long teamId, Long userId) {
        TeamDO team = teamDao.getById(teamId);
        if (team == null) {
            throw BusinessException.newInstance(StatusEnum.RECORDS_NOT_EXISTS, "队伍不存在: " + teamId);
        }

        // 判断该用户是否已组队
        checkGrouped(team.getCompetitionId(), userId);

        if (isFull(team)) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "队伍人数已满");
        }

        teamMemberDao.join(teamId, userId);
    }

    @Override
    public void agree(Long teamId, Long member) {
        Long userId = ReqInfoContext.getReqInfo().getUserId();
        TeamDO team = teamDao.getById(teamId);
        if (team == null || !Objects.equals(team.getCaptain(), userId)) {
            // 只有队长可以同意
            throw BusinessException.newInstance(StatusEnum.FORBID_ERROR);
        }

        // 判断该用户是否已组队
        checkGrouped(team.getCompetitionId(), member);

        if (isFull(team)) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "队伍人数已满");
        }
        teamMemberDao.agree(teamId, member);

        // 组队状态更新
        competitionService.updateGroupStatus(team.getCompetitionId(), member, GroupStatusEnum.GROUP);
    }

    @Override
    public TeamInfoDTO queryTeamInfo(Long competitionId, Long userId) {
        Long teamId = teamDao.getUserTeam(competitionId, userId);
        if (teamId == null) {
            // 还没组队
            return null;
        }
        return buildTeamDetailInfo(teamDao.getById(teamId));
    }

    private TeamInfoDTO buildTeamDetailInfo(TeamDO team) {
        TeamInfoDTO dto = new TeamInfoDTO();
        // 基本信息
        dto.setTeamId(team.getId());
        dto.setCompetitionId(team.getCompetitionId());
        dto.setName(team.getName());

        // 成员信息
        List<TeamMemberDTO> members = teamMemberDao.listTeamMember(team.getId());
        dto.setMembers(members);
        dto.setMemberCount(members.size());
        members.forEach(s -> {
            if (Objects.equals(s.getUserId(), team.getCaptain())) {
                s.setIsCaptain(true);
            }
        });
        return dto;
    }

    /**
     * 判断队伍人数是否已满
     *
     * @param team
     * @return
     */
    private boolean isFull(TeamDO team) {
        Integer maxMemberCount = competitionService.getMaxMemberCount(team.getCompetitionId());
        Integer memberCount = teamMemberDao.getMemberCount(team.getId());
        return maxMemberCount <= memberCount;
    }

    @Override
    public TeamInfoDTO queryTeamByName(Long competitionId, String name) {
        TeamDO team = teamDao.queryByTeamName(competitionId, name);
        return TeamConverter.toDto(team);
    }

    public void checkGrouped(Long competitionId, Long userId) {
        GroupStatusEnum status = competitionService.queryUserGroupStatus(competitionId, userId);
        if (status == GroupStatusEnum.GROUP) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_OPERATE, "用户已组队");
        }
    }

    @Override
    public void quit(Long teamId, Long userId) {
        TeamDO team = teamDao.getById(teamId);
        if (team == null) {
            throw BusinessException.newInstance(StatusEnum.RECORDS_NOT_EXISTS, "队伍不存在: " + teamId);
        }

        teamMemberDao.quit(teamId, userId);

        // 组队状态更新
        competitionService.updateGroupStatus(team.getCompetitionId(), userId, GroupStatusEnum.UNGROUP);
    }
}
