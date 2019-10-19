package com.ly.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @program: spring-data
 * @description: 员工
 * @author: Mr.L
 * @create: 2019-02-05 23:18
 **/
@Alias("emp")
public class Employee implements Serializable {

    private Integer id;
    private String lastName;
    private Integer gender;
    private String email;
    private Integer dId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public Integer getdId() {
        return dId;
    }

}
