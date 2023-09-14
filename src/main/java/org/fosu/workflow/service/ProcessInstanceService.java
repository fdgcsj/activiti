package org.fosu.workflow.service;

import org.fosu.workflow.utils.Result;

import javax.servlet.http.HttpServletResponse;

public interface ProcessInstanceService {
    /**
     * 通过流程实例id获取申请表单
     * @param procInstId 流程实例id
     * @return 数据封装实体类
     */
    Result getFormNameByProcInstId(String procInstId);

    /**
     * 获取流程实例审批历史图
     * @param procInstId 流程实例id
     * @param response 浏览器相应类
     */
    void getHistoryProcessImage(String procInstId, HttpServletResponse response);
}
