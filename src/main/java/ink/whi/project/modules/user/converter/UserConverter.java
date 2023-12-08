package ink.whi.project.modules.user.converter;

import cn.hutool.system.UserInfo;
import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.req.UserSaveReq;
import ink.whi.project.common.enums.RoleEnum;
import ink.whi.project.modules.user.repo.entity.UserDO;
import ink.whi.project.common.domain.dto.BaseUserDTO;
import ink.whi.project.modules.user.repo.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;

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

    public static UserDO toUserDo(UserSaveReq req) {
        UserDO user = new UserDO();
        user.setAccount(req.getUsername());
        user.setPassword(req.getPassword());
        return user;
    }

    public static UserInfoDO toUserInfoDo(UserSaveReq req) {
        if (req == null) {
            return null;
        }
        UserInfoDO info = new UserInfoDO();
        BeanUtils.copyProperties(req, info);
        return info;
    }
}
