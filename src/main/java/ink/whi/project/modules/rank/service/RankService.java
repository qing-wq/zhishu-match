package ink.whi.project.modules.rank.service;

import ink.whi.project.common.domain.dto.RankUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Service
public interface RankService {

    public List<RankUserDTO> getRankWithUserInfo(Long competitionId, Integer page, Integer pageSize);

}
