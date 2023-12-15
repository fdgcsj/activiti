package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("试卷实体类")
@TableName("mxg_paper")
public class Paper {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("试卷名字")
    private String title;
    @ApiModelProperty("试卷类型 1平时测试 2考试")
    private int type;
    @ApiModelProperty("所属课程id")
    private String courseId;
    @ApiModelProperty("创建人用户名")
    private String creator;
    @ApiModelProperty("创建时间")
    private Date createDate;
    @ApiModelProperty("结束答题时间")
    private Date endDate;
}
