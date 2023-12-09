package ink.whi.project.modules.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.CompetitionUpdReq;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
public interface CompetitionService extends IService<CompetitionDO> {
    PageVo<CompetitionDO> list(PageParam pageParam);

    boolean update(CompetitionUpdReq req);

    boolean delete(Long id);

    List<CompetitionDO> listAll(PageParam pageParam);

    Long countAll();

    Integer getMaxMemberCount(Long competitionId);

    void signUp(Long competitionId, Long userId);

    List<BaseUserInfoDTO> queryCompetitionUser(Long competitionId);
}
