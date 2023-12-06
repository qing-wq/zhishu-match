package ink.whi.project.modules.user.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;

/**
 * @author: qing
 * @Date: 2023/10/16
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class UserDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = 4704149522920373163L;

    /**
     * 账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 删除标记
     */
    private Integer deleted;
}
