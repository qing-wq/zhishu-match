package ink.whi.project.modules.competition.repo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.modules.competition.repo.entity.RegisterDO;
import ink.whi.project.modules.competition.repo.mapper.RegisterMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/9
 */
@Repository
public class RegisterDao extends ServiceImpl<RegisterMapper, RegisterDO> {


    public List<Long> listUserByCompetitionId(Long competitionId) {
        List<RegisterDO> list = lambdaQuery()
                .select(RegisterDO::getUserId)
                .eq(RegisterDO::getCompetitionId, competitionId)
                .eq(RegisterDO::getDeleted, YesOrNoEnum.NO.getCode())
                .list();
        System.out.println(list);
        return null;
    }
}
