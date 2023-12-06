package ink.whi.project.common.domain.req;

import lombok.Data;

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
    private String title;

    /**
     * 公告摘要
     */
    private String summary;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告状态 0：未发布 1：已发布
     */
    private Integer status;
}
