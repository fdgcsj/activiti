package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.fosu.workflow.utils.DateUtils;

import java.util.Date;

@Data
@ApiModel("通知实体类")
@TableName("mxg_message")
public class CourseMessage {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("课程名称")
    private String course_name;
    @ApiModelProperty("教师用户名")
    private String teacher_name;
    @ApiModelProperty("教师姓名")
    private String teacher_nick_name;
    @ApiModelProperty("班级id")
    private String class_id;
    @TableField(exist = false)
    @ApiModelProperty("班级名称")
    private String class_name;
    @ApiModelProperty("通知内容")
    private String message;
    @ApiModelProperty("创建时间")
    private Date createDate;

    public String getCreateDateStr() {
        if (createDate == null) {
            return "";
        }
        return DateUtils.format(createDate);
    }
}
