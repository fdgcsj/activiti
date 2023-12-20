package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询话题列表条件")
public class TalkREQ extends BaseRequest {
    @ApiModelProperty("课程id")
    private String courseId;
}
