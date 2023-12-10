package ink.whi.project.modules.user.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户个人信息表
 *
 * @author qing
 * @date 2023/12/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_info", autoResultMap = true)
public class UserInfoDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户头像
     */
    private String picture;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 个人简介
     */
    private String profile;

    /**
     * 扩展字段
     */
    private String extend;

    /**
     * 删除标记
     */
    private Integer deleted;

    /**
     * 0-普通用户，1-超级管理员
     */
    private Integer userRole;

    /**
     * ip信息
     */
    private String ip;
}
