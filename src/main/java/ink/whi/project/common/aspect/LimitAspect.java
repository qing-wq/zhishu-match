package ink.whi.project.common.aspect;

import com.google.common.collect.ImmutableList;
import ink.whi.project.common.annotition.limit.Limit;
import ink.whi.project.common.annotition.limit.LimitType;
import ink.whi.project.common.context.ReqInfoContext;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.common.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 限流规则：计数器限流
 * limit.period() 计数器时间
 * limit.count()  最大请求次数
 */
@Aspect
@Component
public class LimitAspect {

    private final RedisTemplate<Object, Object> redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LimitAspect.class);
    private static final String PREFIX = "LIMIT_";

    public LimitAspect(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("@annotation(ink.whi.project.common.annotition.limit.Limit)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method signatureMethod = signature.getMethod();
        Limit limit = signatureMethod.getAnnotation(Limit.class);

        String key = getCombinKey(limit, signatureMethod, request);

        ImmutableList<Object> keys = ImmutableList.of(key);

        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);

        Number count = redisTemplate.execute(redisScript, keys, limit.count(), limit.period());
        if (null != count && count.intValue() <= limit.count()) {
            logger.info("第{}次访问key为 {}，描述为 [{}] 的接口", count, keys, limit.name());
            return point.proceed();
        } else {
            throw BusinessException.newInstance(StatusEnum.ACCESS_FREQUENT);
        }
    }

    private String getCombinKey(Limit limit, Method signatureMethod, HttpServletRequest request) {
        // combinKey = 资源key + 用户key
        StringBuilder combinKey = new StringBuilder(PREFIX);

        // 资源key
        String key = limit.key();
        combinKey.append(Objects.requireNonNullElseGet(key, signatureMethod::getName)).append("_");

        // 用户key
        LimitType limitType = limit.limitType();
        switch (limitType) {
            case CUSTOMER:
                combinKey.append(ReqInfoContext.getReqInfo().getUserId());
                break;
            case IP:
                combinKey.append(IpUtil.getClientIp(request));
                break;
            default:
                break;
        }
        return combinKey.toString();
    }

    /**
     * 限流脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }
}
