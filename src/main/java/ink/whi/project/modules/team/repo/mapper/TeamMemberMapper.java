package ink.whi.project.modules.team.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.whi.project.common.domain.dto.TeamMemberDTO;
import ink.whi.project.modules.team.repo.entity.TeamDO;
import ink.whi.project.modules.team.repo.entity.TeamMemberDO;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
public interface TeamMemberMapper extends BaseMapper<TeamMemberDO> {
    List<TeamMemberDTO> listMember(Long teamId);
}
