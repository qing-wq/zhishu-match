package ink.whi.project.common.domain.req;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Data
public class CompetitionSaveReq {

    /**
     * 比赛名称
     */
    @NotNull(message = "比赛名称不能为空")
    @Size(min = 1)
    private String name;

    /**
     * 介绍
     */
    @NotNull(message = "介绍不能为空")
    private String description;

    /**
     * 比赛类型
     */
    @Min(value = 1, message = "type的取值只能为1或2 1：个人赛 2：团体赛")
    @Max(value = 2, message = "type的取值只能为1或2 1：个人赛 2：团体赛")
    private Integer type;

    /**
     * 最大人数
     */
    @NotNull(message = "最大人数不能为空")
    private Integer maxMember;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private Date startTime;

    /**
     * 报名截止时间
     */
    @NotNull(message = "报名截止时间不能为空")
    private Date signupDeadline;

    /**
     * 比赛结束时间
     */
    @NotNull(message = "比赛结束时间不能为空")
    private Date endTime;

}
