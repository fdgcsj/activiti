package org.fosu.workflow.service;

import org.fosu.workflow.req.ProcInstREQ;
import org.fosu.workflow.req.StartREQ;
import org.fosu.workflow.utils.Result;

import javax.servlet.http.HttpServletResponse;

public interface ProcessInstanceService {
    /**
     * 通过流程实例id获取申请表单
     *
     * @param procInstId 流程实例id
     * @return 数据封装实体类
     */
    Result getFormNameByProcInstId(String procInstId);

    /**
     * 获取流程实例审批历史图
     *
     * @param procInstId 流程实例id
     * @param response   浏览器相应类
     */
    void getHistoryProcessImage(String procInstId, HttpServletResponse response);

    Result startProcess(StartREQ req);

    Result cancel(String businessKey, String procInstId, String message);

    Result getHistoryInfoList(String procInstId);

    Result getProcInstListRunning(ProcInstREQ req);

    Result updateProcInstState(String procInstId);

    Result deleteProcInst(String procInstId);

    Result getProcInstFinish(ProcInstREQ req);

    Result deleteProcInstAndHistory(String procInstId);
}
