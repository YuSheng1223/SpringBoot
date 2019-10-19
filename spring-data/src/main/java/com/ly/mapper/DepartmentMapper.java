package com.ly.mapper;

import com.ly.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @program: spring-data
 * @description: 利用注解的方式去使用mybatis
 * @author: Mr.L
 * @create: 2019-02-06 20:29
 **/
//@Mapper 指定这个是一个操作数据库的mapper
//@Mapper
public interface DepartmentMapper {
    @Select("select * from department where id = #{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department  where id = #{id}")
    public int deleteDeptById(Integer id);
    //主键自增  指定主键
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName}) ")
    public int insertDept(Department department);

    @Update("update department set departmentName = #{departmentName}  where id = #{id}")
    public int updateDept(Department department);
}
