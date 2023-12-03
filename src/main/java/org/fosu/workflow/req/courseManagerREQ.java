package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("课程管理列表条件")
public class courseManagerREQ extends BaseRequest {
    @ApiModelProperty("课程名称")
    private String course;
    @ApiModelProperty("任课老师")
    private String teacher;
    @ApiModelProperty("所属的用户名")
    private String username;
}
