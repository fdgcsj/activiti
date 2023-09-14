package org.fosu.workflow.service;

import org.fosu.workflow.req.TaskCompleteREQ;
import org.fosu.workflow.req.TaskREQ;
import org.fosu.workflow.utils.Result;
import org.springframework.stereotype.Service;

import java.util.Map;
public interface WaitTaskService {
    Map<String, Object> findWaitTask(TaskREQ req);
    Result getNextNodeInfo(String taskId);
    Result completeTask(TaskCompleteREQ req);
}
