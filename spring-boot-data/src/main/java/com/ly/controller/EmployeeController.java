package com.ly.controller;

import com.ly.bean.Employee;
import com.ly.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: spring-data
 * @description: 员工
 * @author: Mr.L
 * @create: 2019-02-10 23:00
 **/
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/getEmp/{id}")
    public Employee getEmpById(@PathVariable("id")Integer id) {
        logger.info("根据id获取信息");
        Employee empById = employeeService.getEmpById(id);
        return empById;
    }
    @GetMapping("/insertEmp")
    public Employee insertEmp(Employee employee) {
        employeeService.insertEmp(employee);
        return  employee;
    }
    @GetMapping("/updateEmp")
    public Employee updateEmp(Employee employee) {

        employeeService.updateEmp(employee);
        return employee;
    }
    @GetMapping("/deleteEmp/{id}")
    public int deleteEmp(@PathVariable("id") Integer id) {
        return employeeService.deleteEmp(id);
    }
}
