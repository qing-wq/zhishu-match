package ink.whi.project.common.dal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: qing
 * @Date: 2023/12/17
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public SqlStateInterceptor sqlStateInterceptor() {
        return new SqlStateInterceptor();
    }
}
