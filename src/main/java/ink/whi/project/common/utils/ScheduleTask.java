package ink.whi.project.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/14
 */
@Service
@Slf4j
public class ScheduleTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "0 0 0 * * ?") // 每天晚上 00:00:00 执行
//    @Scheduled(fixedRate = 10000)
//    @Scheduled(cron = "0 0 1 * * ?")
    public void performScheduledTask() {
        log.warn("Scheduled task executed at {}", dateFormat.format(new Date()));
        cleanupKeysWithPrefix("NUM_OF_TIMES:");
    }

    public void cleanupKeysWithPrefix(String prefix) {
        Set<String> keys = redisTemplate.keys(prefix + "*");
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

}
