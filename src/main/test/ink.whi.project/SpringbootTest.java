package ink.whi.project;

import ink.whi.project.common.domain.dto.RankUserDTO;
import ink.whi.project.modules.rank.repo.mapper.RankMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.TestOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/8
 */
@SpringBootTest
public class SpringbootTest {

    @Autowired
    RankMapper mapper;

    @Test
    public void Test1(){
    }


}
