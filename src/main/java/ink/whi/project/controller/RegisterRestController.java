package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.modules.competition.repo.dao.RegisterDao;
import ink.whi.project.modules.competition.repo.entity.RegisterDO;
import ink.whi.project.modules.competition.service.CompetitionService;
import ink.whi.project.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 报名接口
 *
 * @author: qing
 * @Date: 2023/12/9
 */
@RestController
@RequestMapping(path = "sign")
public class RegisterRestController {

    @Autowired
    private CompetitionService competitionService;

    @Autowired
    private RegisterDao registerDao;

    /**
     * 用户报名
     *
     * @param competitionId
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping(path = "")
    public ResVo<String> sign(@RequestParam Long competitionId) {
        competitionService.signUp(competitionId, ReqInfoContext.getReqInfo().getUserId());
        return ResVo.ok("ok");
    }

    /**
     * 删除用户报名信息
     *
     * @param userId
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @DeleteMapping(path = "status")
    public ResVo<String> status(@RequestParam Long userId, @RequestParam Long competitionId) {
        registerDao.lambdaUpdate().eq(RegisterDO::getUserId, userId)
                .eq(RegisterDO::getCompetitionId, competitionId)
                .remove();
        return ResVo.ok("ok");
    }
}