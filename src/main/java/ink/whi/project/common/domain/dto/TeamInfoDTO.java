package ink.whi.project.common.domain.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Data
public class TeamInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5846180434571855479L;

    /**
     * 队伍id
     */
    private Long teamId;

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 队伍人数
     */
    private Integer memberCount;

    /**
     * 成员信息
     */
    private List<TeamMemberDTO> members;
}
