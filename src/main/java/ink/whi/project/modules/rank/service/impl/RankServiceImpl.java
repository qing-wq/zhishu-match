package ink.whi.project.modules.rank.service.impl;

import ink.whi.project.common.domain.dto.RankUserDTO;
import ink.whi.project.modules.rank.repo.entity.RankDO;
import ink.whi.project.modules.rank.repo.mapper.RankMapper;
import ink.whi.project.modules.rank.service.RankService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    RankMapper mapper;

    @Override
    public List<RankUserDTO> getRankWithUserInfo(Long competitionId, Integer page, Integer pageSize) {
        return mapper.getRankWithUserInfo(competitionId, new RowBounds((page - 1) * pageSize, pageSize));
    }

    @Override
    public Integer getRankWithUserInfoCount(Long competitionId) {
        return mapper.getRankWithUserInfoCount(competitionId);
    }

    public Integer insert(Long userId, Double score, Long competitionId){
        RankDO rankDO = new RankDO();
        rankDO.setScore(score)
                .setStatus("succeed")
                .setUserId(userId)
                .setCompetitionId(competitionId);
        return mapper.insert(rankDO);
    }
}
