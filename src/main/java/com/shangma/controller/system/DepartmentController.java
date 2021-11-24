package com.shangma.controller.system;

import com.shangma.common.http.AxiosResult;
import com.shangma.entity.system.Department;
import com.shangma.service.system.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:48
 * @Description :
 */
@RestController
@RequestMapping("/system/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public AxiosResult list(){
        System.out.println("进入");
        List<Department> list = departmentService.list();

        return AxiosResult.success(list);
    }

}
