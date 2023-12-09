package ink.whi.project.modules.announcement.converter;

import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.AnnouncementDTO;
import ink.whi.project.common.domain.dto.SimpleAnnouncementDTO;
import ink.whi.project.common.domain.req.AnnouncementReq;
import ink.whi.project.common.enums.PushStatusEnum;
import ink.whi.project.modules.announcement.repo.entity.Announcement;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
public class AnnouncementConverter {

    public static Announcement toDo(AnnouncementReq req) {
        Announcement announcement = new Announcement();
        announcement.setId(req.getAnnouncementId());
        announcement.setTitle(req.getTitle());
        announcement.setContent(req.getContent());
        announcement.setSummary(req.getSummary());
        announcement.setUserId(ReqInfoContext.getReqInfo().getUserId());
        announcement.setStatus(req.getStatus() == null ? PushStatusEnum.OFFLINE.getCode() :PushStatusEnum.formCode(req.getStatus()).getCode());
        return announcement;
    }

    public static SimpleAnnouncementDTO toSimpleDto(Announcement announcement) {
        SimpleAnnouncementDTO simpleAnnouncementDTO = new SimpleAnnouncementDTO();
        simpleAnnouncementDTO.setId(announcement.getId());
        simpleAnnouncementDTO.setCreateTime(announcement.getCreateTime());
        simpleAnnouncementDTO.setUpdateTime(announcement.getUpdateTime());
        simpleAnnouncementDTO.setTitle(announcement.getTitle());
        simpleAnnouncementDTO.setSummary(announcement.getSummary());
        return simpleAnnouncementDTO;
    }

    public static List<SimpleAnnouncementDTO> toDtoList(List<Announcement> list) {
        return list.stream().map(AnnouncementConverter::toSimpleDto).toList();
    }

    public static AnnouncementDTO toDto(Announcement anno) {
        AnnouncementDTO dto = new AnnouncementDTO();
        BeanUtils.copyProperties(anno, dto);
        return dto;
    }
}
