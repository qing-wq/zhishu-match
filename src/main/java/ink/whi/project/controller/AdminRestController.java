package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.modules.competition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员权限接口
 * @author: qing
 * @Date: 2023/12/13
 */
@RestController
@RequestMapping(path = "admin")
@Permission(role = UserRole.ADMIN)
public class AdminRestController {

    @Autowired
    private CompetitionService competitionService;

    /**
     * 查看比赛报名的用户
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
