package ink.whi.project.common.enums;

import lombok.Getter;

/**
 * @author: qing
 * @Date: 2023/12/8
 */
@Getter
public enum CompetitionTypeEnum {

    PERSONAL(1, "个人赛"),
    TEAM(2, "团体赛"),
    ;

    private final Integer code;
    private final String desc;

    CompetitionTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CompetitionTypeEnum formCode(Integer code) {
        for (CompetitionTypeEnum competitionTypeEnum : CompetitionTypeEnum.values()) {
            if (competitionTypeEnum.getCode().equals(code)) {
                return competitionTypeEnum;
            }
        }
        return null;
    }
}
