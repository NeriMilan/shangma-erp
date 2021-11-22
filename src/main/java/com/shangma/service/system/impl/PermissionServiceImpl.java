package com.shangma.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.system.Permission;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.service.system.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:38
 * @Description :
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
