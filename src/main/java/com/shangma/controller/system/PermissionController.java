package com.shangma.controller.system;

import com.shangma.common.http.AxiosResult;
import com.shangma.entity.system.Permission;
import com.shangma.service.system.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 21:24
 * @Description :
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    public AxiosResult list(){
        List<Permission> list = permissionService.list();

        return AxiosResult.success(list);
    }


}
