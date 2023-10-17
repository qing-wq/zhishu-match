package ink.whi.project.service.user.repo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.service.user.converter.UserConverter;
import ink.whi.project.service.user.repo.entity.UserDO;
import ink.whi.project.service.user.repo.mapper.UserMapper;
import ink.whi.project.vo.dto.BaseUserDTO;
import org.springframework.stereotype.Repository;

/**
 * @author: qing
 * @Date: 2023/4/26
 */
@Repository
public class UserDao extends ServiceImpl<UserMapper, UserDO> {

    public BaseUserDTO getByUserNameAndPwd(String username, String password) {
        UserDO user = lambdaQuery().eq(UserDO::getUserName, username)
                .eq(UserDO::getPassword, password)
                .eq(UserDO::getDeleted, 0)
                .one();
        return UserConverter.toDTO(user);
    }
}
