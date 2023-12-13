package ink.whi.project.common.enums;

import lombok.Getter;

/**
 * @author: qing
 * @Date: 2023/12/10
 */
@Getter
public enum GroupStatusEnum {

    NOT_GROUP(0, "未组队"),
    GROUP(1, "已组队")
    ;

    private final Integer code;
    private final String msg;

    GroupStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static GroupStatusEnum formCode(int code) {
        for (GroupStatusEnum statusEnum : GroupStatusEnum.values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }
        }
        return null;
    }
}
