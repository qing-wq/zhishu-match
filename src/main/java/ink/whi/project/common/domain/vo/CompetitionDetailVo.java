package ink.whi.project.common.domain.vo;

import ink.whi.project.common.domain.dto.CompetitionDTO;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/12/9
 */
@Data
public class CompetitionDetailVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 3712888842164239909L;

    /**
     * 比赛信息
     */
    private CompetitionDTO competition;

    /**
     * 当前用户是否报名
     */
    private boolean isRegister;
}