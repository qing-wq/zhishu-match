package ink.whi.project.modules.team.service;

import ink.whi.project.common.domain.dto.TeamInfoDTO;
import ink.whi.project.common.domain.req.TeamSaveReq;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
public interface TeamService {
    Long createTeam(TeamSaveReq teamSaveReq);

    void join(Long teamId, Long userId);

    void agree(Long teamId, Long userId);

    TeamInfoDTO queryTeamInfo(Long competitionId, Long userId);

    TeamInfoDTO queryTeamByName(Long competitionId, String name);

    void quit(Long teamId, Long userId);
}
