package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.RankTeamDTO;
import ink.whi.project.common.domain.dto.TeamInfoDTO;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.common.rest_template.req.EvaluateReq;
import ink.whi.project.common.rest_template.resp.EvaluateResp;
import ink.whi.project.common.utils.RestTemplateUtil;
import ink.whi.project.controller.base.BaseRestController;
import ink.whi.project.modules.rank.service.RankService;
import ink.whi.project.modules.team.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 排行榜接口
 * @author: chenyi0008
 * @Date: 2023/12/7
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/rank")
public class RankController extends BaseRestController {

    @Autowired
    RankService rankService;

    @Autowired
    RestTemplateUtil restTemplateUtil;

    @Autowired
    TeamService teamService;


    private final RedisTemplate<String, String> redisTemplate;
    private static final String PREFIX = "NUM_OF_TIMES";
    public RankController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private Integer getValueByKey(String key) {
        String s = redisTemplate.opsForValue().get(PREFIX + ":" + key);
        return s == null ? null : Integer.parseInt(redisTemplate.opsForValue().get(PREFIX + ":" + key));
    }
    private void saveValueByKey(String key, Long value) {
        redisTemplate.opsForValue().set(PREFIX + ":" + key, value.toString());
    }
    private Long incrementValue(String key) {
        return redisTemplate.opsForValue().increment(PREFIX + ":" + key);
    }


    /**
     * 排行榜
     * @param page
     * @param pageSize
     * @param competitionId
     * @return
     */
    @GetMapping
    public ResVo<PageVo<RankTeamDTO>> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                            @RequestParam(name = "competitionId") Long competitionId){
        List<RankTeamDTO> list = rankService.getRankWithUserInfo(competitionId, page, pageSize);
        Integer count = rankService.getRankWithUserInfoCount(competitionId);
        PageVo<RankTeamDTO> pageVo = PageVo.build(list, pageSize, page, count);

        Long userId = ReqInfoContext.getReqInfo().getUserId();
        if(userId == null){
            return ResVo.ok(pageVo);
        }

        TeamInfoDTO teamInfoDTO = teamService.queryTeamInfo(competitionId, userId);
        if(teamInfoDTO == null){
            return ResVo.ok(pageVo);
        }

        for (RankTeamDTO rankTeamDTO : list) {
            if (rankTeamDTO.getTeamId().equals(teamInfoDTO.getTeamId())) rankTeamDTO.setIsSelf(true);
        }

        return ResVo.ok(pageVo);
    }

    @Permission(role = UserRole.LOGIN)
    @GetMapping("/times")
    public ResVo<Integer> getTimes(@RequestParam Long competitionId){
        Long userId = ReqInfoContext.getReqInfo().getUserId();
        TeamInfoDTO teamInfoDTO = teamService.queryTeamInfo(competitionId, userId);
        if(teamInfoDTO == null)throw new BusinessException(StatusEnum.UNEXPECT_ERROR, "请先组队");
        Integer valueByKey = getValueByKey(teamInfoDTO.getTeamId().toString());
        if(valueByKey != null){
            log.info("times:{}", 5 - valueByKey);
            return ResVo.ok(5 -valueByKey);
        }else{
            log.info("times:{}", 5);
            return ResVo.ok(5);
        }

    }

    @Permission(role = UserRole.LOGIN)
    @PostMapping("/upload")
    public ResVo<String> handleFileUpload(@RequestPart("file") MultipartFile file, @RequestParam Long competitionId) throws IOException {
        log.info("competitionId:{}", competitionId);

        //限制次数
        int time = 0;
        Long userId = ReqInfoContext.getReqInfo().getUserId();

        TeamInfoDTO teamInfoDTO = teamService.queryTeamInfo(competitionId, userId);
        if(teamInfoDTO == null)throw new BusinessException(StatusEnum.UNEXPECT_ERROR, "请先组队");

        Integer valueByKey = getValueByKey(teamInfoDTO.getTeamId().toString());
        log.info("redis key:{}", teamInfoDTO.getTeamId().toString());
        log.info("redis times:{}", valueByKey);
        if(valueByKey != null){
            time = valueByKey;
        }

        if(time >= 5)throw new BusinessException(StatusEnum.UNEXPECT_ERROR,"队伍上传次数超过5次！");


        String fileExtension = getFileExtension(file.getOriginalFilename());
        // 处理格式问题
        if(!fileExtension.equals("csv"))throw new BusinessException(StatusEnum.UNEXPECT_ERROR," 请上传csv文件");

        // 上传文件并且测评分数
        String name = restTemplateUtil.uploadFile(file.getBytes(), file.getOriginalFilename());

        String url = "http://10.60.98.106:7860";
        EvaluateReq req = new EvaluateReq(url, name, (int) file.getSize());

        EvaluateResp evaluate;
        try{
            evaluate = restTemplateUtil.evaluate(req);
        }catch(Exception e){
            log.warn("error:{}", e.getMessage());
            throw new BusinessException(StatusEnum.UNEXPECT_ERROR, "文件无效！");
        }

        Double score = Double.valueOf(evaluate.getData().get(0));
        // 往数据库中插入此评测记录
        Integer insert = rankService.insert(userId, score, competitionId, teamInfoDTO.getTeamId());

        if(insert > 0){
            if(time == 0){
                saveValueByKey(String.valueOf(teamInfoDTO.getTeamId()), 1L);
            }else{
                incrementValue(String.valueOf(teamInfoDTO.getTeamId()));
            }
            return ResVo.ok("本次测评的分数为：" + score);
        }else{
            return ResVo.fail(StatusEnum.UNEXPECT_ERROR, "测评失败");
        }

    }

    public static String getFileExtension(String filename) {
        if (filename != null && filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }
        throw new BusinessException(StatusEnum.UNEXPECT_ERROR, "文件名无效！请上传格式为csv的文件");
    }

}
