package ink.whi.project.modules.rank.repo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Data
@Accessors(chain = true)
@TableName("rank")
public class RankDO extends BaseDO {

    Long userId;

    String status;

    Double score;

}
