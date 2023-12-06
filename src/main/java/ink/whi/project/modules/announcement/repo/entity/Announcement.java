package ink.whi.project.modules.announcement.repo.entity;

import ink.whi.project.common.domain.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Announcement extends BaseDO {
    @Serial
    private static final long serialVersionUID = 7340460936934717221L;
    /**
     * 创建人
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 状态
     * {@link ink.whi.project.common.enums.PushStatusEnum}
     */
    private Integer status;

}
