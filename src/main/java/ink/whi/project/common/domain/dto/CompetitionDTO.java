package ink.whi.project.common.domain.dto;

import ink.whi.project.common.enums.CompetitionTypeEnum;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/9
 */
@Data
public class CompetitionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5129279111140474622L;

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 比赛名称
     */
    private String name;

    /**
     * 介绍
     */
    private String description;

    /**
     * 比赛类型
     * {@link CompetitionTypeEnum}
     */
    private Integer type;

    /**
     * 最大人数
     */
    private Integer maxMember;

    /**
     * 比赛开始时间
     */
    private Data beginTime;

    /**
     * 比赛结束时间
     */
    private Data endTime;
}
