package com.shangma.controller.system;

import com.shangma.common.http.AxiosResult;
import com.shangma.entity.system.RolePermission;
import com.shangma.service.system.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 21:26
 * @Description :
 */
@RestController
@RequestMapping("/system/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;



}
