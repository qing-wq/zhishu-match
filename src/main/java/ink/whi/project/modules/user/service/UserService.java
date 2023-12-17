package ink.whi.project.modules.user.service;

import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.req.UserSaveReq;

/**
 * @author: qing
 * @Date: 2023/4/26
 */
public interface UserService {
    BaseUserInfoDTO queryBasicUserInfo(Long userId);

    BaseUserInfoDTO passwordLogin(String username, String password);

    Long saveUser(UserSaveReq req);

    void updateIpInfo(Long user, String clientIp);

    Long countTotal();
}
