package ink.whi.project.service.user.repo.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.whi.project.service.user.repo.entity.UserDO;
import ink.whi.project.service.user.repo.mapper.UserMapper;
import org.springframework.stereotype.Repository;

/**
 * @author: qing
 * @Date: 2023/4/26
 */
@Repository
public class UserDao extends ServiceImpl<UserMapper, UserDO> {

}
