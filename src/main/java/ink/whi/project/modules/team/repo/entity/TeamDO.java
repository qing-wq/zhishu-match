package ink.whi.project.modules.team.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("team")
public class TeamDO extends BaseDO {

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 队长id
     */
    private Long captain;

    /**
     * 是否删除
     */
    private Integer deleted;


}
