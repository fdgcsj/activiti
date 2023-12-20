package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("考勤管理实体类")
@TableName("mxg_attend_list")
public class AttendList {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("考勤表id")
    private String attend_id;
    @ApiModelProperty("学生姓名")
    private String student_name;
    @ApiModelProperty("学生学号")
    private String student_id;
    @ApiModelProperty("考勤状态")
    private String status;
}
