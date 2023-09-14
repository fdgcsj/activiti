package org.fosu.workflow.req;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
@Data
@ApiModel("条件查询任务请求类")
public class TaskREQ extends BaseRequest {
    @ApiModelProperty("任务名称")
    private String taskName;
}