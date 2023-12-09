package ink.whi.project.modules.competition.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.whi.project.common.domain.dto.SimpleAnnouncementDTO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.CompetitionUpdReq;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
public interface CompetitionService extends IService<CompetitionDO> {
    public PageVo<CompetitionDO> list(PageParam pageParam);

    public boolean update(CompetitionUpdReq req);

    public boolean delete(Long id);

    public List<CompetitionDO> listAll(PageParam pageParam);

    public Long countAll();

    Integer getMaxMemberCount(Long competitionId);
}
