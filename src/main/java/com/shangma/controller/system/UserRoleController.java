package com.shangma.controller.system;

import com.shangma.common.http.AxiosResult;
import com.shangma.entity.system.UserRole;
import com.shangma.service.system.JobService;
import com.shangma.service.system.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 21:27
 * @Description : 预删除
 */
@RestController
@RequestMapping("/system/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/list")
    public AxiosResult list(){
        List<UserRole> list = userRoleService.list();

        return AxiosResult.success(list);
    }

}
