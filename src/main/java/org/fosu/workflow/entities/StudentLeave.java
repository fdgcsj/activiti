package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.fosu.workflow.enums.BusinessStatusEnum;
import org.fosu.workflow.utils.DateUtils;

import java.util.Date;

@Data
@ApiModel("请假管理实体类")
@TableName("mxg_student_leave")
public class StudentLeave {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("学生用户名")
    private String name;
    @ApiModelProperty("课程名称")
    private String course_name;
    @ApiModelProperty("教师姓名")
    private String teacher_nick_name;
    @ApiModelProperty("上课时间")
    private String time;
    @ApiModelProperty("请假理由")
    private String reason;
    @ApiModelProperty("创建时间")
    private Date createDate;
    @TableField(exist = false)
    @ApiModelProperty("流程实例id")
    private String processInstanceId;
    @TableField(exist = false)
    @ApiModelProperty("流程状态")
    private Integer status;

    public String getStatusStr() {
        if (this.status == null) {
            return "";
        }
        return BusinessStatusEnum.getEumByCode(this.status).getDesc();
    }

    public String getCreateDateStr() {
        if (createDate == null) {
            return "";
        }
        return DateUtils.format(createDate);
    }

}
