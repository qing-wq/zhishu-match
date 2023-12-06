package ink.whi.project.common.domain.vo;

import ink.whi.project.common.exception.Status;
import ink.whi.project.common.exception.StatusEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: qing
 * @Date: 2023/4/25 23:52
 */
@Data
public class ResVo<T> implements Serializable {
    private static final long serialVersionUID = -510306209659393854L;

    private Status status;

    private T result;

    public ResVo() {
    }

    public ResVo(Status status) {
        this.status = status;
    }

    public ResVo(T result) {
        this.status = Status.newStatus(StatusEnum.SUCCESS);
        this.result = result;
    }

    public static <T> ResVo<T> ok(T result) {
        return new ResVo<T>(result);
    }

    public static <T> ResVo<T> fail(StatusEnum statusEnum, Object... args) {
        return new ResVo<>(Status.newStatus(statusEnum, args));
    }

    public static <T> ResVo<T> fail(Status status) {
        return new ResVo<>(status);
    }
}

