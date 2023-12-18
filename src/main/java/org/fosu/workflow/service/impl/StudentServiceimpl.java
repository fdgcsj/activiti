package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Student;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.StudentMapper;
import org.fosu.workflow.mapper.courseManagerMapper;
import org.fosu.workflow.service.StudentService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceimpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    public Result getStudentsByClass(String classId){
        ArrayList<Student> studentList=baseMapper.getStudentsByClass(classId);
        return Result.ok(studentList);
    };
    public Boolean isStudent(String username){
        ArrayList<Student> studentList=baseMapper.isStudent(username);
        boolean isstudent=false;
        if (studentList.size()!=0){
            isstudent=true;
        }
        System.out.println(username);
        System.out.println(studentList);
        System.out.println(studentList.size());
        System.out.println(isstudent);
        return isstudent;
    };
}
