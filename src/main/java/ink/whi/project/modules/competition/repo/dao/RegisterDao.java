package ink.whi.project.modules.competition.repo.dao;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.modules.competition.repo.entity.RegisterDO;
import ink.whi.project.modules.competition.repo.mapper.RegisterMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/9
 */
@Repository
public class RegisterDao extends ServiceImpl<RegisterMapper, RegisterDO> {


    public List<Long> listUserByCompetitionId(Long competitionId) {
        // 索引下推优化
        List<RegisterDO> list = lambdaQuery()
                .select(RegisterDO::getUserId)
                .eq(RegisterDO::getCompetitionId, competitionId)
                .eq(RegisterDO::getDeleted, YesOrNoEnum.NO.getCode())
                .list();
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list.stream().map(RegisterDO::getUserId).toList();
    }

    public RegisterDO getRecord(Long userId, Long competitionId) {
        return lambdaQuery().eq(RegisterDO::getUserId, userId)
                .eq(RegisterDO::getCompetitionId, competitionId)
                .eq(RegisterDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
    }

    public Long listUserCompetition(Long userId) {
        RegisterDO record = lambdaQuery()
                .select(RegisterDO::getCompetitionId)
                .eq(RegisterDO::getUserId, userId)
                .eq(RegisterDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
        if (record == null) {
            return null;
        }

        return record.getCompetitionId();
    }
}
