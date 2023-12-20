package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("回复实体类")
@TableName("mxg_talk")
public class Talk {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("所属课程id")
    private String courseId;
    @ApiModelProperty("话题标题")
    private String title;
    @ApiModelProperty("话题简介")
    private String text;
    @ApiModelProperty("创建人用户名")
    private String creator;
    @ApiModelProperty("创建时间")
    private Date createDate;
}
