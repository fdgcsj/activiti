package org.fosu.workflow.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询话题发言列表条件")
public class SpeakREQ extends BaseRequest{
    @ApiModelProperty("话题id")
    private String talkId;
    @ApiModelProperty("顺时排序")
    private boolean ascendSort;
}
