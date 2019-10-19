package com.ly.service;

import com.ly.bean.Employee;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: spring-data
 * @description: service
 * @author: Mr.L
 * @create: 2019-02-10 22:43
 **/
public interface EmployeeService {

    public Employee getEmpById(Integer id);

    public Employee insertEmp(Employee employee);

    public Employee updateEmp(Employee employee);

    public int deleteEmp(Integer did);

}
