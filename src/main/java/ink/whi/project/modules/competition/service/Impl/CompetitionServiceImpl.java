package ink.whi.project.modules.competition.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.common.domain.base.BaseDO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.CompetitionUpdReq;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import ink.whi.project.modules.competition.repo.mapper.CompetitionMapper;
import ink.whi.project.modules.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, CompetitionDO> implements CompetitionService {


    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    @Override
    public PageVo<CompetitionDO> list(PageParam pageParam) {
        List<CompetitionDO> list = this.listAll(pageParam);
        return PageVo.build(list, pageParam.getPageSize(), pageParam.getPageNum(), this.countAll());
    }

    /**
     * 更新
     * @param req
     * @return
     */
    public boolean update(CompetitionUpdReq req){
        boolean update = lambdaUpdate().eq(CompetitionDO::getId, req.getId())
                .set(CompetitionDO::getName, req.getName())
                .set(CompetitionDO::getDescription, req.getDescription()).update();
        return update;
    }

    /**
     * 软删除
     * @param id
     * @return
     */
    public boolean delete(Long id){
        boolean update = lambdaUpdate().eq(CompetitionDO::getId, id)
                .set(CompetitionDO::getDeleted,YesOrNoEnum.YES.getCode()).update();
        return update;
    }

    public List<CompetitionDO> listAll(PageParam pageParam) {
        return lambdaQuery()
                .eq(CompetitionDO::getDeleted, YesOrNoEnum.NO.getCode())
                .orderByDesc(BaseDO::getCreateTime)
                .last(PageParam.getLimitSql(pageParam))
                .list();
    }

    public Long countAll() {
        return lambdaQuery().eq(CompetitionDO::getDeleted, YesOrNoEnum.NO.getCode())
                .count();
    }
}
