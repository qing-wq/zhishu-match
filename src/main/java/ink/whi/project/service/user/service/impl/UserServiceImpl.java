package ink.whi.project.service.user.service.impl;

import ink.whi.project.service.user.converter.UserConverter;
import ink.whi.project.service.user.repo.dao.UserDao;
import ink.whi.project.service.user.repo.entity.UserDO;
import ink.whi.project.service.user.service.UserService;
import ink.whi.project.vo.dto.BaseUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: qing
 * @Date: 2023/4/26
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public BaseUserDTO queryBasicUserInfo(Long userId) {
        UserDO user = userDao.getById(userId);
        return UserConverter.toDTO(user);
    }

    @Override
    public BaseUserDTO passwordLogin(String username, String password) {
        return userDao.getByUserNameAndPwd(username, password);
    }
}
