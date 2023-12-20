package org.fosu.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.fosu.workflow.entities.Class;
import org.fosu.workflow.entities.Student;
import org.fosu.workflow.utils.Result;

public interface StudentService extends IService<Student> {
    Result getStudentsByClass(String classId);

    Boolean isStudent(String username);
}
