package ink.whi.project.controller;

import ink.whi.project.common.domain.dto.RankUserDTO;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.controller.base.BaseRestController;
import ink.whi.project.modules.rank.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 排行榜接口
 * @author: chenyi0008
 * @Date: 2023/12/7
 */
@Slf4j
@RestController
@RequestMapping("/rank")
public class RankController extends BaseRestController {

    @Autowired
    RankService rankService;

    @GetMapping
    public ResVo<PageVo<RankUserDTO>> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                            @RequestParam(name = "competitionId") Long competitionId){
        List<RankUserDTO> list = rankService.getRankWithUserInfo(competitionId, page, pageSize);
        Integer count = rankService.getRankWithUserInfoCount(competitionId);
        PageVo<RankUserDTO> pageVo = new PageVo<>(list, pageSize, page, count);

        return ResVo.ok(pageVo);
    }

}
