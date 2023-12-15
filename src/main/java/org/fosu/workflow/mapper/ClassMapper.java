package org.fosu.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.fosu.workflow.entities.Class;
import org.fosu.workflow.entities.courseManager;
import org.fosu.workflow.req.courseManagerREQ;
import org.fosu.workflow.utils.Result;

import java.util.ArrayList;

public interface ClassMapper extends BaseMapper<Class> {
    ArrayList<Class> getAllClasses();

    ArrayList<Class> getTeacherClasses(String username);


}
