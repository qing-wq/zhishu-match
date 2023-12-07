package ink.whi.project.controller;

import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.CompetitionSaveReq;
import ink.whi.project.common.domain.req.CompetitionUpdReq;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.controller.base.BaseRestController;
import ink.whi.project.modules.competition.repo.entity.CompetitionDO;
import ink.whi.project.modules.competition.service.CompetitionService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@RestController
@RequestMapping("/competition")
public class CompetitionController extends BaseRestController {
    @Autowired
    private CompetitionService competitionService;

//    @Permission(role = UserRole.ADMIN)
    @PostMapping
    public ResVo create(@Validated @RequestBody CompetitionSaveReq req){
        CompetitionDO competitionDO = new CompetitionDO();
        boolean save = competitionService.save(competitionDO);
        if(save){
            return ResVo.ok("添加成功");
        }else{
            return ResVo.ok("添加失败");
        }
    }


    @GetMapping
    public ResVo<PageVo<CompetitionDO>> list(@RequestParam(name = "page", required = false) Long page,
                      @RequestParam(name = "pageSize", required = false) Long pageSize){
        PageParam pageParam = buildPageParam(page, pageSize);
        PageVo<CompetitionDO> list = competitionService.list(pageParam);
        return ResVo.ok(list);
    }

    @PutMapping
    public ResVo update(@Validated @RequestBody CompetitionUpdReq req){
        CompetitionDO competitionDO = new CompetitionDO();
        boolean update = competitionService.update(req);
        if(update){
            return ResVo.ok("更新成功");
        }else{
            return ResVo.ok("更新失败");
        }
    }

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
