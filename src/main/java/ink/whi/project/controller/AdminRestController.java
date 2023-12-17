package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.dto.StatisticsDTO;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.modules.competition.service.CompetitionService;
import ink.whi.project.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserService userService;

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

    /**
     * 获取网站用户总数
     * @return
     */
    @GetMapping(path = "count")
    public ResVo<Long> count() {
        Long count = userService.countTotal();
        return ResVo.ok(count);
    }

    /**
     * 查看每个比赛报名的人数
     * @return
     */
    @GetMapping(path = "statistics")
    public ResVo<List<StatisticsDTO>> statistics() {
        List<StatisticsDTO> statistic = competitionService.getStatistic();
        return ResVo.ok(statistic);
    }
}
