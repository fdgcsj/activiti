package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Leave;
import org.fosu.workflow.entities.Student;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;

import java.util.ArrayList;
import java.util.List;

public interface StudentMapper extends BaseMapper<Student> {

    ArrayList<Student> getStudentsByClass(String classId);

    ArrayList<Student> isStudent(String username);

}
