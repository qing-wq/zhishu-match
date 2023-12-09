package ink.whi.project.common.domain.req;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Data
public class TeamSaveReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -8348410466069107725L;

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 队长id
     */
    private Long captain;

    /**
     * 队伍名称
     */
    private String name;
}
