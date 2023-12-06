package ink.whi.project.modules.user.converter;

import cn.hutool.system.UserInfo;
import ink.whi.project.common.domain.req.UserSaveReq;
import ink.whi.project.modules.user.repo.entity.UserDO;
import ink.whi.project.common.domain.dto.BaseUserDTO;
import ink.whi.project.modules.user.repo.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;

/**
 * 实体转换
 * @author: qing
 * @Date: 2023/4/26
 */
public class UserConverter {

    public static BaseUserDTO toDTO(UserInfoDO user) {
        if (user == null) {
            return null;
        }

        BaseUserDTO dto = new BaseUserDTO();
        BeanUtils.copyProperties(user, user);
        return dto;
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
