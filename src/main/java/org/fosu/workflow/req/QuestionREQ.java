package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询题目列表条件")
public class QuestionREQ extends BaseRequest {
    @ApiModelProperty("试卷id")
    private String paperId;
}
