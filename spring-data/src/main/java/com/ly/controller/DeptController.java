package com.ly.controller;

import com.ly.bean.Department;
import com.ly.bean.Employee;
import com.ly.mapper.DepartmentMapper;
import com.ly.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-data
 * @description: 部门
 * @author: Mr.L
 * @create: 2019-02-06 20:34
 **/
@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDepartment(Department department){
         departmentMapper.insertDept(department);
        return department;
    }
//    @GetMapping("emp/{id}")
//    public Employee getEmp(@PathVariable("id") Integer id){
//        return  employeeMapper.getEmpById(id);
//    }


}
