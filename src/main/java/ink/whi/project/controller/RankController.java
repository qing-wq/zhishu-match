package ink.whi.project.controller;

import ink.whi.project.common.domain.dto.RankUserDTO;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.common.feign.req.EvaluateReq;
import ink.whi.project.common.feign.resp.EvaluateResp;
import ink.whi.project.common.feign.resp.UploadResp;
import ink.whi.project.common.utils.RestTemplateUtil;
import ink.whi.project.controller.base.BaseRestController;
import ink.whi.project.modules.rank.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/rank")
public class RankController extends BaseRestController {

    @Autowired
    RankService rankService;

    @Autowired
    RestTemplateUtil restTemplateUtil;

    @GetMapping
    public ResVo<PageVo<RankUserDTO>> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                            @RequestParam(name = "competitionId") Long competitionId){
        List<RankUserDTO> list = rankService.getRankWithUserInfo(competitionId, page, pageSize);
        Integer count = rankService.getRankWithUserInfoCount(competitionId);
        PageVo<RankUserDTO> pageVo = PageVo.build(list, pageSize, page, count);

        return ResVo.ok(pageVo);
    }


    @PostMapping("/upload")

    public ResVo<String> handleFileUpload(@RequestPart("file") MultipartFile file) throws IOException {
        log.info("Received file: {}", file.getOriginalFilename());

        String name = restTemplateUtil.uploadFile(file.getBytes(), file.getOriginalFilename());

        String url = "http://10.60.98.106:7860";
        int size = 8888;

        log.info("fileUrl:{}", name);
        EvaluateReq req = new EvaluateReq(url, name, size);
        EvaluateResp evaluate = restTemplateUtil.evaluate(req);
        log.info("Evaluate:{}", evaluate.toString());
        return ResVo.ok("本次测评的分数为：" + evaluate.getData().get(0));
    }

}
