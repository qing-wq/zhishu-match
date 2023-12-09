package ink.whi.project.modules.team.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import ink.whi.project.common.enums.TeamStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("team_member")
public class TeamMemberDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = -2796163036609161850L;

    /**
     * 团队id
     */
    private Long teamId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 组队状态
     * {@link TeamStatusEnum}
     */
    private Integer status;
}
