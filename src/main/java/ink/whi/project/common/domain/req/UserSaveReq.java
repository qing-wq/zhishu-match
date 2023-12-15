package ink.whi.project.common.domain.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
     * 姓名
     */
    @NotNull
    @Length(min = 1, max = 10)
    private String name;

    /**
     * 学号
     */
    @NotNull
    @Length(min = 10, max = 10)
    private String studentId;

    /**
     * 电话号码
     */
    @NotNull
    private String phone;

    /**
     * 账号（邮箱）
     */
    @Email
    private String email;

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
