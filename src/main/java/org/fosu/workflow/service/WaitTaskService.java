package org.fosu.workflow.service;

import org.fosu.workflow.req.CompleteBackTaskREQ;
import org.fosu.workflow.req.TaskCompleteREQ;
import org.fosu.workflow.req.TaskREQ;
import org.fosu.workflow.utils.Result;
import org.springframework.stereotype.Service;

import java.util.Map;
public interface WaitTaskService {
    Map<String, Object> findWaitTask(TaskREQ req);
    Result getNextNodeInfo(String taskId);
    Result completeTask(TaskCompleteREQ req);
    Result turnTask(String taskId, String assigneeUserKey);
    Result getBackNodes(String taskId);
    Result backProcess(String taskId, String targetActivityId);
    Result findCompleteTask(TaskREQ req);
}
