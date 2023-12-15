package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询答题列表条件")
public class AnswerREQ extends BaseRequest {
    @ApiModelProperty("试卷id")
    private String paperId;
    @ApiModelProperty("题目id")
    private String questionId;
    @ApiModelProperty("答题人用户名")
    private String creator;
}
