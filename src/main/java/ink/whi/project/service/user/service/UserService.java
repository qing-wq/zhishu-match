package ink.whi.project.service.user.service;

import ink.whi.project.vo.dto.BaseUserDTO;

/**
 * @author: qing
 * @Date: 2023/4/26
 */
public interface UserService {
    BaseUserDTO queryBasicUserInfo(Long userId);

    BaseUserDTO passwordLogin(String username, String password);
}
