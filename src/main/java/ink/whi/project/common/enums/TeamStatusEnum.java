package ink.whi.project.common.enums;

import lombok.Getter;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Getter
public enum TeamStatusEnum {

    NOT_JOIN(0, "未加入"),
    WAIT(1, "待通过"),
    JOINED(2, "已加入"),
    ;

    private final Integer code;
    private final String msg;

    TeamStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static TeamStatusEnum formCode(int code) {
        for (TeamStatusEnum statusEnum : TeamStatusEnum.values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }
        }
        return NOT_JOIN;
    }
}
