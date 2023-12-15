package org.fosu.workflow.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("选择题实体类")
@TableName("mxg_question_choice")
public class ChoiceQuestion {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("所属试卷id")
    private String paperId;
    @ApiModelProperty("题目描述")
    private String description;
    @ApiModelProperty("选项A")
    private String choiceA;
    @ApiModelProperty("选项B")
    private String choiceB;
    @ApiModelProperty("选项C")
    private String choiceC;
    @ApiModelProperty("选项D")
    private String choiceD;
    @ApiModelProperty("答案")
    private int answer;
    @ApiModelProperty("分数")
    private int score;
    @ApiModelProperty("创建时间")
    private Date createDate;
}
