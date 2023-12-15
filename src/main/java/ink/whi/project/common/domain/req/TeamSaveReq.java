package ink.whi.project.common.domain.req;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Data
@NoArgsConstructor
public class TeamSaveReq implements Serializable {
    @Serial
    private static final long serialVersionUID = -8348410466069107725L;

    /**
     * 比赛id
     */
    @NotNull
    private Long competitionId;

    /**
     * 队长id
     */
    private Long captain;

    /**
     * 队伍名称
     */
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
}
