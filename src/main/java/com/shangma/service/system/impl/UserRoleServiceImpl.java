package com.shangma.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.system.UserRole;
import com.shangma.mapper.system.UserRoleMapper;
import com.shangma.service.system.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:43
 * @Description :
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
