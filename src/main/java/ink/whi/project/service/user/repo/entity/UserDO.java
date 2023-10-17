package ink.whi.project.service.user.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.vo.base.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: qing
 * @Date: 2023/10/16
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class UserDO extends BaseDO {
    private static final long serialVersionUID = 4704149522920373163L;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 删除标记
     */
    private Integer deleted;

}
