package ink.whi.project.common.domain.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Data
public class CompetitionSaveReq {

    @NotNull(message = "比赛名称不能为空")
    @Size(min = 1)
    private String name;

    @NotNull(message = "介绍不能为空")
    private String description;

}
