package ink.whi.project.common.domain.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Data
public class TeamMemberDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 361388602627295805L;

    /**
     * 队伍id
     */
    private Long teamId;

    /**
     * 队员信息
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 队员状态 0-未加入 1-待通过 2-已加入
     * {@link ink.whi.project.common.enums.TeamStatusEnum}
     */
    private Integer status;

    /**
     * 是否是队长
     */
    private Boolean isCaptain = false;
}
