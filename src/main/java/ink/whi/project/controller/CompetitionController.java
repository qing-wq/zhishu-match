package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.CompetitionSaveReq;
import ink.whi.project.common.domain.req.CompetitionUpdReq;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.controller.base.BaseRestController;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import ink.whi.project.modules.competition.repo.mapper.CompetitionMapper;
import ink.whi.project.modules.competition.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 比赛接口
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Slf4j
@RestController
@RequestMapping("competition")
public class CompetitionController extends BaseRestController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * 创建比赛
     * @param req
     * @return
     */
//    @Permission(role = UserRole.ADMIN)
    @PostMapping("/create")
    public ResVo<String> create(@Validated @RequestBody CompetitionSaveReq req){
        CompetitionDO competitionDO = new CompetitionDO();
        BeanUtils.copyProperties(req, competitionDO);
        boolean save = competitionService.save(competitionDO);
        if(save){
            return ResVo.ok("添加成功");
        }else{
            return ResVo.fail(StatusEnum.UNEXPECT_ERROR, "添加失败");
        }
    }

    /**
     * 查询比赛列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping
    public ResVo<PageVo<CompetitionDO>> list(@RequestParam(name = "page", required = false) Long page,
                                             @RequestParam(name = "pageSize", required = false) Long pageSize){

        PageParam pageParam = buildPageParam(page, pageSize);
        PageVo<CompetitionDO> list = competitionService.list(pageParam);
        return ResVo.ok(list);
    }

    /**
     * 更新比赛
     * @param req
     * @return
     */
    @PutMapping
    public ResVo update(@Validated @RequestBody CompetitionUpdReq req){
        boolean update = competitionService.update(req);
        if(update){
            return ResVo.ok("更新成功");
        }else{
            return ResVo.ok("更新失败");
        }
    }

    /**
     * 删除比赛
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResVo delete(@PathVariable("id") Long id){
        boolean delete = competitionService.delete(id);
        if(delete){
            return ResVo.ok("删除成功");
        }else{
            return ResVo.ok("删除失败");
        }
    }

}
