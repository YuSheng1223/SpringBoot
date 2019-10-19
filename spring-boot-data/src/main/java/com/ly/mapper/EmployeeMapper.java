package com.ly.mapper;

import com.ly.bean.Employee;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeMapper {


    public Employee  getEmpById(Integer id);

    public int insertEmp(Employee employee);

    public int updateEmp(Employee employee);

    public int deleteEmp(Integer id);
}
