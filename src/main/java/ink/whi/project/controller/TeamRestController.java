package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.TeamInfoDTO;
import ink.whi.project.common.domain.req.TeamSaveReq;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.modules.team.service.TeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 组队接口
 *
 * @author: qing
 * @Date: 2023/12/8
 */
@RestController
@RequestMapping(path = "team")
public class TeamRestController {

    @Autowired
    private TeamService teamService;

    /**
     * 创建队伍
     *
     * @param
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @PostMapping(path = "create")
    public ResVo<Long> createTeam(@Validated @RequestBody TeamSaveReq req) {
        Long userId = ReqInfoContext.getReqInfo().getUserId();
        req.setCaptain(userId);
        Long teamId = teamService.createTeam(req);
        return ResVo.ok(teamId);
    }

    /**
     * 加入队伍
     *
     * @param teamName
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping(path = "join")
    public ResVo<String> join(@RequestParam String teamName) {
        if (StringUtils.isBlank(teamName)) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "队伍名称不能为空");
        }

        teamService.join(teamName, ReqInfoContext.getReqInfo().getUserId());
        return ResVo.ok("申请成功，待队长通过");
    }

    /**
     * 同意入队
     *
     * @param teamId 队伍id
     * @param userId 成员id
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping(path = "agree")
    public ResVo<String> agree(@RequestParam Long teamId, @RequestParam Long userId) {
        teamService.agree(teamId, userId);
        return ResVo.ok("ok");
    }

    /**
     * 查看个人组队信息
     * @param competitionId 竞赛id
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping(path = "{competitionId}")
    public ResVo<TeamInfoDTO> team(@PathVariable Long competitionId) {
        Long userId = ReqInfoContext.getReqInfo().getUserId();
        TeamInfoDTO info = teamService.queryTeamInfo(competitionId, userId);
        return ResVo.ok(info);
    }

    /**
     * 根据队伍名称获取队伍信息
     * @param name
     * @param competitionId
     * @return 队伍id 队伍名称
     */
    @GetMapping(path = "search")
    public ResVo<TeamInfoDTO> team(@RequestParam String name, @RequestParam Long competitionId) {
        TeamInfoDTO dto = teamService.queryTeamByName(competitionId, name);
        return ResVo.ok(dto);
    }

    /**
     * 退出队伍
     *
     * @param teamId
     * @return
     */
    @Permission(role = UserRole.LOGIN)
    @GetMapping(path = "quit")
    public ResVo<String> quit(@RequestParam Long teamId) {
        // 退出团队
        teamService.quit(teamId, ReqInfoContext.getReqInfo().getUserId());
        return ResVo.ok("ok");
    }
}
