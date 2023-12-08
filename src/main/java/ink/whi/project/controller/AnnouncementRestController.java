package ink.whi.project.controller;

import ink.whi.project.common.annotition.permission.Permission;
import ink.whi.project.common.annotition.permission.UserRole;
import ink.whi.project.common.domain.dto.AnnouncementDTO;
import ink.whi.project.common.domain.dto.SimpleAnnouncementDTO;
import ink.whi.project.common.domain.page.PageParam;
import ink.whi.project.common.domain.page.PageVo;
import ink.whi.project.common.domain.req.AnnouncementReq;
import ink.whi.project.common.domain.vo.ResVo;
import ink.whi.project.common.utils.SpringUtil;
import ink.whi.project.controller.base.BaseRestController;
import ink.whi.project.modules.announcement.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

/**
 * 公告接口
 * @author: qing
 * @Date: 2023/12/5
 */
@RestController
@RequestMapping(path = "announcement")
public class AnnouncementRestController extends BaseRestController {

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 发布/更新 公告
     * @param req
     * @return 公告id
     */
    @Permission(role = UserRole.ADMIN)
    @PostMapping(path = "post")
    public ResVo<Long> post(@RequestBody AnnouncementReq req) {
        Long annoId = announcementService.post(req);
        return ResVo.ok(annoId);
    }

    /**
     * 公告列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(path = "list")
    public ResVo<PageVo<SimpleAnnouncementDTO>> list(@RequestParam(name = "page", required = false) Long page,
                                                     @RequestParam(name = "pageSize", required = false) Long pageSize) {
        PageParam pageParam = buildPageParam(page, pageSize);
        PageVo<SimpleAnnouncementDTO> list = announcementService.list(pageParam);
        return ResVo.ok(list);
    }

    /**
     * 公告详情
     * @param id
     * @return
     */
    @GetMapping(path = "detail/{id}")
    public ResVo<AnnouncementDTO> detail(@PathVariable(name = "id") Long id) {
        AnnouncementDTO announcementDTO = announcementService.detail(id);
        return ResVo.ok(announcementDTO);
    }

    /**
     * 提取摘要
     * @param content
     * @return
     */
    @GetMapping(path = "summary")
    public ResVo<String> summary(@RequestParam(name = "content") String content) {
        String summary = announcementService.generateSummary(content);
        return ResVo.ok(summary);
    }
}
