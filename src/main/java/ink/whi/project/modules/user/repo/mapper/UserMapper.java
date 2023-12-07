package ink.whi.project.modules.user.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.whi.project.modules.user.repo.entity.UserDO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UserMapper extends BaseMapper<UserDO> {
}
