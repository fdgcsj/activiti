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
@ApiModel("考勤管理实体类")
@TableName("mxg_attend")
public class Attend {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("课程名称")
    private String name;
    @ApiModelProperty("教师用户名")
    private String TchId;
    @TableField(exist = false)
    @ApiModelProperty("教师姓名")
    private String teacher_name;
    @ApiModelProperty("上课时间")
    private String time;
    @ApiModelProperty("学生总人数")
    private String student_num;
    @ApiModelProperty("创建时间")
    private Date createDate;
    @ApiModelProperty("更新时间")
    private Date updateDate;

    public String getCreateDateStr() {
        if (createDate == null) {
            return "";
        }
        return DateUtils.format(createDate);
    }

    public String getUpdateDateStr() {
        if (updateDate == null) {
            return "";
        }
        return DateUtils.format(updateDate);
    }
}
