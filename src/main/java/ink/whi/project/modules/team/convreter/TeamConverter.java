package ink.whi.project.modules.team.convreter;

import ink.whi.project.common.domain.dto.TeamInfoDTO;
import ink.whi.project.common.domain.dto.TeamMemberDTO;
import ink.whi.project.common.domain.req.TeamSaveReq;
import ink.whi.project.modules.team.repo.entity.TeamDO;
import ink.whi.project.modules.team.repo.entity.TeamMemberDO;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
public class TeamConverter {


    public static TeamDO toDo(TeamSaveReq req) {
        if (req == null) {
            return null;
        }

        TeamDO team = new TeamDO();
        team.setCompetitionId(req.getCompetitionId());
        team.setCaptain(req.getCaptain());
        team.setName(req.getName());
        return team;
    }

    public static TeamMemberDTO toDto(TeamMemberDO teamMemberDO) {
        if (teamMemberDO == null) {
            return null;
        }

        TeamMemberDTO dto = new TeamMemberDTO();
        dto.setTeamId(teamMemberDO.getTeamId());
        dto.setUserId(teamMemberDO.getUserId());
        dto.setStatus(teamMemberDO.getStatus());
        return dto;
    }

    public static List<TeamMemberDTO> toDtoList(List<TeamMemberDO> list) {
        return list.stream().map(TeamConverter::toDto).toList();
    }

    public static TeamInfoDTO toDto(TeamDO team) {
        if (team == null) {
            return null;
        }

        TeamInfoDTO dto = new TeamInfoDTO();
        BeanUtils.copyProperties(team, dto);
        dto.setTeamId(team.getId());
        return dto;
    }
}
