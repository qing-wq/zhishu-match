package ink.whi.project.common.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/8
 */

@Data
public class RankTeamDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 队伍名称
     */
    private String name;
    /**
     * 分数
     */
    private Double score;
    /**
     * 队伍id
     */
    private Long teamId;
    /**
     * 是否是自己队伍
     */
    private Boolean isSelf = false;



}
