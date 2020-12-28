package com.rlj.springbootweb.controller;

import com.rlj.springbootweb.dao.DepartmentDao;
import com.rlj.springbootweb.dao.EmployeeDao;
import com.rlj.springbootweb.entities.Department;
import com.rlj.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工，返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        System.out.println("121");
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //来到添加页面，查出所有部门，在页面显示
    @GetMapping("/emp")
    public String toAddPage(Model model){
        System.out.println("222222");
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //执行添加操作（将添加页面发送过来数据添加进来）
    @PostMapping("/emp")
    //SpringMVC自动将请求参数和下面括号的入参对象的属性名一一绑定，要求请求参数各name属性的值
    //和Java的Bean对象的属性名是一样的
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        //添加完成后，还是应该来到员工列表页面。这里一定要注意，请求会被thymeleaf拼串，这里我们
        //如果直接返回list，确实会去往那个页面，但是由于没有经过查询所有员工的controller层方法，
        //那么将没有数据被加载。因此我们需要返回给上面的list(Model model)方法，但是为了不让
        //thymeleaf拼串，我们要使用请求转发(redirect:/)或者请求重定向(forward:/)
        return "redirect:/emps";
    }

    //来到添加页面，但是由于是修改，所以要回显请求id对应的员工信息
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        //来到添加页面，查出id对应的员工信息、所有部门，在页面显示
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp",employee);
        model.addAttribute("depts",departments);
        return "emp/add";//add是一个修改添加二合一的页面
    }

    //执行修改操作
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);//这个employee中有id
        return "redirect:/emps";
    }

    //执行删除操作
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);//这个employee中有id
        return "redirect:/emps";
    }
}
