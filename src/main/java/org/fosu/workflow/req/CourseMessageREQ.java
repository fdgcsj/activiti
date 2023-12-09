package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("查询课程通知列表条件")
public class CourseMessageREQ extends BaseRequest{
    @ApiModelProperty("课程名称")
    private String course;
    @ApiModelProperty("班级")
    private String classes;
    @ApiModelProperty("所属的用户名")
    private String username;
}
