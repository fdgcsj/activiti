package org.fosu.workflow.entities;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("发言实体类")
@TableName("mxg_speak")
public class Speak {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("所属话题id")
    private String talkId;
    @ApiModelProperty("讲话内容")
    private String text;
    @ApiModelProperty("讲话人用户名")
    private String speaker;
    @ApiModelProperty("创建时间")
    private Date createDate;
}
