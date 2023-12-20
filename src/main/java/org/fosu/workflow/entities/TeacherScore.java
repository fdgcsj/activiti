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
@ApiModel("教学反馈实体类")
@TableName("mxg_teacher_score")
public class TeacherScore {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("课程名称")
    private String name;
    @ApiModelProperty("用户名")
    private String tch_id;
    @ApiModelProperty("教师姓名")
    private String nick_name;
    @ApiModelProperty("课程评分1")
    private String score1;
    @ApiModelProperty("课程评分2")
    private String score2;
    @ApiModelProperty("课程评分3")
    private String score3;
    @ApiModelProperty("课程评分4")
    private String score4;
    @TableField(exist = false)
    @ApiModelProperty("课程平均线")
    private String average_score;
}
