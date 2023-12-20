package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("班级实体类")
@TableName("mxg_class")
public class Class {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("班级名称")
    private String name;
    @TableField(exist = false)
    @ApiModelProperty("班级名称")
    private String class_name;
    @TableField(exist = false)
    @ApiModelProperty("班级id")
    private String class_id;
    @ApiModelProperty("班主任用户名")
    private String head_teacher;
    @ApiModelProperty("班主任姓名")
    private String head_teacher_nick_name;
    @ApiModelProperty("辅导员用户名")
    private String counselor;
    @ApiModelProperty("辅导员姓名")
    private String counselor_nick_name;
}
