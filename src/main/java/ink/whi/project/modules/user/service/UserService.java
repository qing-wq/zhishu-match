package ink.whi.project.modules.user.service;

import ink.whi.project.common.domain.dto.BaseUserDTO;
import ink.whi.project.common.domain.req.UserSaveReq;

/**
 * @author: qing
 * @Date: 2023/4/26
 */
public interface UserService {
    BaseUserDTO queryBasicUserInfo(Long userId);

    BaseUserDTO passwordLogin(String username, String password);

    Long saveUser(UserSaveReq req);
}
