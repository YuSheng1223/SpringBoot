package com.ly.service.serviceImpl;

import com.ly.bean.Employee;
import com.ly.mapper.EmployeeMapper;
import com.ly.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: spring-data
 * @description:
 * @author: Mr.L
 * @create: 2019-02-10 22:45
 **/
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //获取id 取参数id缓存用户
    @Override
    @Transactional
    @Cacheable(value = "redisCache",key = "'redis_emp_'+#id")
    public Employee getEmpById(Integer id) {
        logger.info("从缓存中获取则没有此条信息，从DB中获取才会有这条信息");
        return employeeMapper.getEmpById(id);
    }
    //插入用户 回填id 取结果id缓存用户
    @Override
    @Transactional
    @CachePut(value = "redisCache",key = "'redis_emp_'+#result.id")
    public Employee insertEmp(Employee employee) {
         employeeMapper.insertEmp(employee);
         return  employee;
    }
    // 更新数据后 更新缓存 如果condition配置项结果返回null不缓存
    @Override
    @Transactional
    @CachePut(value = "redisCache",condition = "#result != 'null'",key = "'redis_emp_'+#result.id")
    public Employee updateEmp(Employee employee) {
        // 这里会调用getEmpById 方法，该方法的缓存失效
        //所以这里还会执行sql 获得最新的数据
        Employee emp = this.getEmpById(employee.getId());
        if (emp ==null){
            return null;
        }
        employeeMapper.updateEmp(employee);
        return employee;
    }
    //在方法执行之后移除缓存
    @Override
    @Transactional
    @CacheEvict(value = "redisCache",key = "'redis_emp_'+#id",beforeInvocation = false)
    public int deleteEmp(Integer id) {
        return employeeMapper.deleteEmp(id);
    }
}
