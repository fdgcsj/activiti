package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("答题实体类")
@TableName("mxg_answer")
public class Answer {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("试卷id")
    private String paperId;
    @ApiModelProperty("题目id")
    private String questionId;
    @ApiModelProperty("答案")
    private String answer;
    @ApiModelProperty("得分")
    private int score;
    @ApiModelProperty("答题人用户名")
    private String creator;
}
