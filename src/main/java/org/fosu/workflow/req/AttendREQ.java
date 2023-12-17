package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询请假管理列表条件")
public class AttendREQ extends BaseRequest{
    @ApiModelProperty("课程名称")
    private String course;
    @ApiModelProperty("创建时间")
    private String createtime;
    @ApiModelProperty("所属的用户名")
    private String username;
}
