package ink.whi.project;

import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.controller.helper.LoginHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyTest extends BasicTest{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void test() {
        String password = "123456";
        System.out.println(passwordEncoder.encode(password));
    }
}
