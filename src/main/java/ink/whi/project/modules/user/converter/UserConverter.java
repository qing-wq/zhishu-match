package ink.whi.project.modules.user.converter;

import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.req.UserSaveReq;
import ink.whi.project.common.enums.RoleEnum;
import ink.whi.project.modules.user.repo.entity.UserDO;
import ink.whi.project.modules.user.repo.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 实体转换
 *
 * @author: qing
 * @Date: 2023/4/26
 */
public class UserConverter {

    public static BaseUserInfoDTO toDTO(UserInfoDO info) {
        if (info == null) {
            return null;
        }

        BaseUserInfoDTO user = new BaseUserInfoDTO();
        BeanUtils.copyProperties(info, user);
        user.setRole(RoleEnum.role(info.getUserRole()));
        return user;
    }

    public static UserDO toDo(UserSaveReq req) {
        if (req == null) {
            return null;
        }

        UserDO user = new UserDO();
        user.setAccount(req.getEmail());
        user.setPassword(req.getPassword());
        return user;
    }

    public static UserInfoDO toUserInfoDo(UserSaveReq req) {
        if (req == null) {
            return null;
        }

        UserInfoDO info = new UserInfoDO();
        BeanUtils.copyProperties(req, info);
        info.setRealName(req.getName());
        return info;
    }

    public static List<BaseUserInfoDTO> toDtoList(List<UserInfoDO> list) {
        return list.stream().map(UserConverter::toDTO).toList();
    }
}
