package spring.boot.crud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.boot.crud.dao.DepartmentDao;
import spring.boot.crud.dao.EmployeeDao;
import spring.boot.crud.entities.Department;
import spring.boot.crud.entities.Employee;

import java.util.Collection;

/**
 * @program: sprng-boot-crud
 * @description: 员工 restful风格编程  修改put  查get 新增post 删除delete
 * @author: Mr.L
 * @create: 2019-01-13 15:39
 **/
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;
    /**
    * @Description: 查询所有员工返回列表页面
    * @Author: Mr.L
    * @Date: 2019/1/13 0013
    */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee>  employees = employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps", employees);
//        thymleaf 默认拼串
        // tempaltes/emps/list.html

        return "emp/list";
    }
    /** 跳转到员工添加页面
    * @Description:
    * @Author: Mr.L
    * @Date: 2019/1/19 0019
    */
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查询所有部门 在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }
    /**
    * @Description:添加员工
    * @Author: Mr.L
    * @Date: 2019/1/19 0019
    */
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println(employee.toString());
        employeeDao.save(employee);
        //redirect 表示重定向
        //forward  表示转发
        // /  代表当前项目下的emps 就是员工列表页面
        return "redirect:/emps";
    }
    /**
    * @Description:   查询当前员工 在页面回显
    * @Author: Mr.L
    * @Date: 2019/1/19 0019
    */
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id")Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "emp/add";
    }
    //员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println(employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id")Integer id){
        System.out.println("要删除的员工id"+id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
