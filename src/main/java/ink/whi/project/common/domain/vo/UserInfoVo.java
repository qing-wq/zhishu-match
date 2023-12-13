package ink.whi.project.common.domain.vo;

import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/13
 */
@Data
public class UserInfoVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 2164483138877061665L;

    /**
     * 用户信息
     */
    private BaseUserInfoDTO user;

    /**
     * 用户报名的比赛id
     */
    private Long competitionId;
}
