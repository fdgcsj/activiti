package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Class;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.utils.Result;

public interface ClassService extends IService<Class> {
    Result getAllClasses();
}
