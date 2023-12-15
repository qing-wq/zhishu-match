package ink.whi.project.common.domain.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
@Data
public class AnnouncementReq implements Serializable {

    @Serial
    private static final long serialVersionUID = -8723581794696455902L;

    /**
     * 公告id（非必须）
     */
    private Long announcementId;

    /**
     * 公告标题
     */
    @NotNull(message = "公告标题不能为空")
    @Size(min = 1, max = 100)
    private String title;

    /**
     * 公告摘要
     */
    @NotNull(message = "摘要不能为空")
    @Size(min = 1, max = 300)
    private String summary;

    /**
     * 公告内容
     */
    @NotNull(message = "内容不能为空")
    private String content;

    /**
     * 公告状态 0：未发布 1：已发布
     */
    private Integer status;
}
