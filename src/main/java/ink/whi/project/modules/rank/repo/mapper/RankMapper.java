package ink.whi.project.modules.rank.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.whi.project.common.domain.dto.RankTeamDTO;
import ink.whi.project.modules.rank.repo.entity.RankDO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
public interface RankMapper extends BaseMapper<RankDO> {

    @Select("SELECT r.*, t.* FROM (" +
            "  SELECT r.team_id, MAX(r.score) as score " +
            "  FROM (" +
            "    SELECT team_id, score, competition_id , `status`" +
            "    FROM `rank` " +
            "    WHERE competition_id = #{competitionId} AND `status` = 'succeed'" +
            "  ) AS r " +
            "  GROUP BY r.team_id" +
            ") AS r " +
            "JOIN team t ON r.team_id = t.id " +
            "ORDER BY score DESC")
    List<RankTeamDTO> getRankWithUserInfo(Long competitionId, RowBounds rowBounds);

    @Select("SELECT COUNT(*) FROM (" +
            "  SELECT r.team_id " +
            "  FROM (" +
            "    SELECT team_id, MAX(score) as score " +
            "    FROM `rank` " +
            "    WHERE competition_id = #{competitionId} AND `status` = 'succeed' " +
            "    GROUP BY team_id" +
            "  ) AS r" +
            ") AS r")
    Integer getRankWithUserInfoCount(Long competitionId);




}
