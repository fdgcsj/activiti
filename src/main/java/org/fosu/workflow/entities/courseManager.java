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
@ApiModel("课程管理实体类")
@TableName("mxg_course")
public class courseManager {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("课程名称")
    private String name;
    @ApiModelProperty("教师用户名")
    private String tchId;
    @ApiModelProperty("教师姓名")
    private String nick_name;
    @ApiModelProperty("上课时间")
    private String time;
    @ApiModelProperty("上课教室")
    private String room;
    @ApiModelProperty("班级id")
    private String classId;
    @ApiModelProperty("创建时间")
    private Date createDate;
    @ApiModelProperty("更新时间")
    private Date updateDate;
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

    public String getUpdateDateStr() {
        if (updateDate == null) {
            return "";
        }
        return DateUtils.format(updateDate);
    }
}
