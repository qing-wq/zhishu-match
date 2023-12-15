package ink.whi.project.controller.helper;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ink.whi.project.common.domain.vo.EmailVo;
import ink.whi.project.common.exception.BusinessException;
import ink.whi.project.common.exception.StatusEnum;
import ink.whi.project.common.utils.CodeGenerateUtil;
import ink.whi.project.common.utils.EmailUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: qing
 * @Date: 2023/12/5
 */
@Component
public class LoginHelper {

    // key-code  val-email
    private final LoadingCache<String, String> verifyCodeCache;

    public LoginHelper() {
        verifyCodeCache = CacheBuilder.newBuilder().maximumSize(300).expireAfterWrite(5, TimeUnit.MINUTES).build(
                new CacheLoader<String, String>() {
                    @Override
                    public @NotNull String load(@NotNull String key) throws Exception {
                        throw BusinessException.newInstance(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "验证码错误");
                    }
                }
        );
    }

    /**
     * 获取邮箱验证码
     *
     * @param email
     */
    @Async
    public void subscribe(String email) {
        String code = CodeGenerateUtil.genCode();
        verifyCodeCache.put(code, email);

        // send email
        EmailVo vo = new EmailVo();
        vo.setTo(email);
        vo.setContent(code);
        boolean success = EmailUtil.sendMail(vo);
        if (!success) {
            throw BusinessException.newInstance(StatusEnum.UNEXPECT_ERROR, "邮件发送失败");
        }
    }

    /**
     * 验证邮箱验证码
     *
     * @param email
     * @param code
     */
    public void verifyEmail(String email, String code) {
        String plainEmail = verifyCodeCache.getIfPresent(code);
        if (StringUtils.isBlank(plainEmail) || !Objects.equals(plainEmail, email)) {
            throw BusinessException.newInstance(StatusEnum.ILLEGAL_ARGUMENTS_MIXED, "验证码错误");
        }
    }
}
