package ink.whi.project.modules.user.service.impl;

import ink.whi.project.common.domain.dto.BaseUserInfoDTO;
import ink.whi.project.common.domain.req.UserSaveReq;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.modules.user.converter.UserConverter;
import ink.whi.project.modules.user.repo.dao.UserDao;
import ink.whi.project.modules.user.repo.entity.UserDO;
import ink.whi.project.modules.user.repo.entity.UserInfoDO;
import ink.whi.project.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public BaseUserInfoDTO queryBasicUserInfo(Long userId) {
        UserInfoDO user = userDao.getByUserId(userId);
        if (user == null) {
            throw BusinessException.newInstance(StatusEnum.USER_NOT_EXISTS, userId);
        }
        return UserConverter.toDTO(user);
    }

    @Override
    public BaseUserInfoDTO passwordLogin(String username, String password) {
        UserDO user = userDao.getUserByName(username);
        if (user == null) {
            throw BusinessException.newInstance(StatusEnum.USER_NOT_EXISTS, username);
        }

        // 密码加密
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw BusinessException.newInstance(StatusEnum.USER_PWD_ERROR);
        }

        return queryBasicUserInfo(user.getId());
    }

    @Override
    @Transactional
    public Long saveUser(UserSaveReq req) {
        UserDO user = UserConverter.toDo(req);
        UserDO record = userDao.getUserByName(user.getAccount());
        if (record != null) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "用户已存在，请登录");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
        UserInfoDO userInfo = UserConverter.toUserInfoDo(req);
        userInfo.setUserId(user.getId());

        // 生成默认初始用户名
        userInfo.setUserName("zhishu_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8));
        userDao.save(userInfo);
        return user.getId();
    }
}
