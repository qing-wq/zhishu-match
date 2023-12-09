package ink.whi.project.modules.competition.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import ink.whi.project.common.enums.CompetitionTypeEnum;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.Date;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Data
@Accessors(chain = true)
@TableName("competition")
public class CompetitionDO extends BaseDO {

     @Serial
     private static final long serialVersionUID = -3887090033109593542L;

     /**
      * 比赛名称
      */
     private String name;

     /**
      * 介绍
      */
     private String description;

     /**
      * 比赛类型
      * {@link CompetitionTypeEnum}
      */
     private Integer type;

     /**
      * 最大人数
      */
     private Integer maxMember;

     /**
      * 开始时间
      */
     private Date startTime;

     /**
      * 结束时间
      */
     private Date endTime;

     /**
      * 报名截止时间
      */
     private Date signupDeadline;

     /**
      * 是否删除
      */
     private Integer deleted;
}
