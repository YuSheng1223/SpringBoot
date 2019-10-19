package com.ly.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @program: spring-data
 * @description:部门
 * @author: Mr.L
 * @create: 2019-02-05 23:19
 **/
@Alias("department")
public class Department implements Serializable {
    private Integer id;
    private String departmentName;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
