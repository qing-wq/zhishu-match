package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.modules.competition.service.CompetitionService;
import ink.whi.project.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 报名接口
 * @author: qing
 * @Date: 2023/12/9
 */
@RestController
@RequestMapping(path = "sign")
public class RegisterRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompetitionService competitionService;

    /**
     * 用户报名
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
     * 查看用户报名信息
     * @param competitionId
     * @return
     */
    @Permission(role = UserRole.ADMIN)
    @GetMapping(path = "user")
    public ResVo<List<BaseUserInfoDTO>> user(Long competitionId) {
        List<BaseUserInfoDTO> list = competitionService.queryCompetitionUser(competitionId);
        return ResVo.ok(list);
    }
}
