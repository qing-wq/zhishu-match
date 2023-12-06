package ink.whi.project.common.domain.dto;

import ink.whi.project.common.domain.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author: qing
 * @Date: 2023/12/6
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnnouncementDTO extends BaseDTO {
    @Serial
    private static final long serialVersionUID = -6608273904798219231L;

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
}
