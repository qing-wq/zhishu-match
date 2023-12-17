package ink.whi.project.common.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/16
 */
@Data
public class StatisticsDTO implements Serializable {

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 比赛名称
     */
    private String competitionName;

    /**
     * 报名人数
     */
    private Long userCount;

    /**
     * 队伍数
     */
    private Long teamCount;
}
