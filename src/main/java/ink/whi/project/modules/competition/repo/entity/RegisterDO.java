package ink.whi.project.modules.competition.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author: qing
 * @Date: 2023/12/9
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("register")
public class RegisterDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = -3728401369764921707L;

    /**
     * 比赛id
     */
    private Long competitionId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 是否删除
     */
    private Integer deleted;
}
