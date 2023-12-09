package ink.whi.project;

import ink.whi.project.modules.competition.repo.dao.RegisterDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyTest extends BasicTest{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RegisterDao registerDao;

    @Test
    void test() {
        String password = "123456";
        System.out.println(passwordEncoder.encode(password));
    }

    @Test
    void test2() {
        registerDao.listUserByCompetitionId(1L);
    }
}
