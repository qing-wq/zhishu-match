package ink.whi.project.modules.user.repo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.common.enums.YesOrNoEnum;
import ink.whi.project.modules.user.repo.entity.UserInfoDO;
import ink.whi.project.modules.user.repo.mapper.UserInfoMapper;
import ink.whi.project.modules.user.repo.mapper.UserMapper;
import ink.whi.project.modules.user.repo.entity.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: qing
 * @Date: 2023/12/4
 */
@Repository
public class UserDao extends ServiceImpl<UserInfoMapper, UserInfoDO> {

    @Autowired
    private UserMapper userMapper;

    public UserDO getUserByName(String username) {
        LambdaQueryWrapper<UserDO> query = Wrappers.lambdaQuery();
        query.eq(UserDO::getAccount, username)
                .eq(UserDO::getDeleted, YesOrNoEnum.NO.getCode());
        return userMapper.selectOne(query);
    }

    public void saveUser(UserDO user) {
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
    }

    public UserInfoDO getByUserId(Long userId) {
        return lambdaQuery().eq(UserInfoDO::getUserId, userId)
                .eq(UserInfoDO::getDeleted, YesOrNoEnum.NO.getCode())
                .one();
    }
}
