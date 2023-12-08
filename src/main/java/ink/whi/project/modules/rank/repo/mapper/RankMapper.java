package ink.whi.project.modules.rank.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.whi.project.common.domain.dto.RankUserDTO;
import ink.whi.project.modules.rank.repo.entity.RankDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
public interface RankMapper extends BaseMapper<RankDO> {

    @Select("SELECT r.*, u.* FROM (" +
            "  SELECT r.user_id, MAX(r.score) as score " +
            "  FROM (" +
            "    SELECT user_id, score, competition_id , `status`" +
            "    FROM `rank` " +
            "    WHERE competition_id = #{competitionId} AND `status` = 'succeed'" +
            "  ) AS r " +
            "  GROUP BY r.user_id" +
            ") AS r " +
            "JOIN user_info u ON r.user_id = u.user_id " +
            "ORDER BY score DESC")
    List<RankUserDTO> getRankWithUserInfo(Long competitionId,  RowBounds rowBounds);





}
