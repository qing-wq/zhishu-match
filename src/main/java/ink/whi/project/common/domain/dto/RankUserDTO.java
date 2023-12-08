package ink.whi.project.common.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/8
 */

@Data
public class RankUserDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 分数
     */
    private Double score;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 头像
     */
    private String photo;

}
