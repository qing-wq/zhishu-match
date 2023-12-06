package ink.whi.project.common.domain.req;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
@Data
public class UserSaveReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 34489978092087873L;

    /**
     * 账号（邮箱）
     */
    @NotNull
    private String username;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 验证码
     */
    @NotNull
    private String code;
}
