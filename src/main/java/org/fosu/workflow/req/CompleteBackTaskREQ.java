package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("执行驳回的任务请求类")
public class CompleteBackTaskREQ implements Serializable {
    @ApiModelProperty("任务Id")
    private String taskId;
    @ApiModelProperty("目标活动任务Id")
    private String targetActivityId;
}
