package ink.whi.project.common.domain.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Data
public class CompetitionUpdReq {

    /**
     * id
     */
    @NotNull
    @Min(1)
    private Long Id;

    /**
     * 比赛名称
     */
    @NotNull
    @Size(min = 1)
    private String name;

    /**
     * 介绍
     */
    @NotNull
    private String description;

}
