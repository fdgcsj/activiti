package org.fosu.workflow.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.fosu.workflow.entities.Class;
import org.fosu.workflow.entities.Loan;
import org.fosu.workflow.entities.Student;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.mapper.ClassMapper;
import org.fosu.workflow.mapper.courseManagerMapper;
import org.fosu.workflow.service.ClassService;
import org.fosu.workflow.service.courseManagerService;
import org.fosu.workflow.utils.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassServiceimpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
    public Result getAllClasses(){
        ArrayList<Class> ClassesList=baseMapper.getAllClasses();
        return Result.ok(ClassesList);
    };

    public Result getTeacherClasses(String username){
        ArrayList<Class> ClassesList=baseMapper.getTeacherClasses(username);
        return Result.ok(ClassesList);
    };

}
