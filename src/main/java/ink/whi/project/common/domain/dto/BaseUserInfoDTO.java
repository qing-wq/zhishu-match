package ink.whi.project.common.domain.dto;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class BaseUserInfoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2426438424647735636L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户角色 必须是String
     */
    private String role;

    /**
     * 用户头像
     */
    private String picture;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 邮箱
     */
    private String email;
}
