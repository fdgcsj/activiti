package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学生实体类")
@TableName("mxg_class")
public class Student {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("学生用户名")
    private String name;
    @ApiModelProperty("学生姓名")
    private String nick_name;
    @ApiModelProperty("学号")
    private String student_id;
    @ApiModelProperty("班级id")
    private String classid;
    @ApiModelProperty("专业")
    private String major;
}
