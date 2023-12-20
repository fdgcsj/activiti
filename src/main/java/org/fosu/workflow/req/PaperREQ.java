package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询试卷列表条件")
public class PaperREQ extends BaseRequest{
    @ApiModelProperty("试卷类型")
    private int type;
    @ApiModelProperty("试卷名")
    private String title;
    @ApiModelProperty("课程id")
    private String courseId;
    @ApiModelProperty("创建人")
    private String creator;
}
