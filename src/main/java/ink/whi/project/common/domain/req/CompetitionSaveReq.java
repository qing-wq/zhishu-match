package ink.whi.project.common.domain.req;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Data
public class CompetitionSaveReq {

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    private String description;

}
