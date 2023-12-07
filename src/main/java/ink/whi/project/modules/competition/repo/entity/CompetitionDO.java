package ink.whi.project.modules.competition.repo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import ink.whi.project.common.domain.base.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description
 * @Author chenyi0008
 * @Date 2023/12/7
 */
@Data
@Accessors(chain = true)
@TableName("competition")
public class CompetitionDO extends BaseDO {

     /**
      * 比赛名字
      */
     private String name;

     /**
      * 介绍
      */
     private String description;

     /**
      * 是否删除
      */
     private Integer deleted;

}
