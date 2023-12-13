package ink.whi.project.modules.team.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.whi.project.modules.team.repo.entity.TeamDO;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
public interface TeamMapper extends BaseMapper<TeamDO> {
    Long selectUserTeam(Long competition, Long userId);
}
