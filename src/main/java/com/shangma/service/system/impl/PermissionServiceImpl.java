package com.shangma.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.system.Permission;
import com.shangma.entity.system.RolePermission;
import com.shangma.entity.system.RolePermissionExample;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.PermissionVO;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.mapper.system.RolePermissionMapper;
import com.shangma.service.system.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:38
 * @Description :
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<PermissionVO> condition(PermissionVO permissionVO) {
        return permissionMapper.condition(permissionVO);
    }

    @Override
    public PermissionVO getById(Long id) {
        return permissionMapper.selectVoById(id);
    }

    @Override
    public boolean removeById(Long id, User master) {

        int i = permissionMapper.deleteByPrimaryKey(id);

        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andPermissionIdEqualTo(id);
        for (RolePermission rolePermission : rolePermissionMapper.selectByExample(rolePermissionExample)) {
            rolePermission.setUpdateId(master.getId());
            rolePermissionMapper.updateById(rolePermission);
        }
        rolePermissionMapper.deleteByExample(rolePermissionExample);

        return i == 1;
    }

    @Override
    public boolean updateById(PermissionVO permissionVO, User master) {

        permissionVO.setUpdateId(master.getId());
        int i = permissionMapper.updateById(permissionVO);

        return i == 1;
    }

    @Override
    public boolean add(PermissionVO permissionVO, User master) {

        permissionVO.setCreateId(master.getId());
        permissionVO.setUpdateId(master.getId());

        int i = permissionMapper.insertSelective(permissionVO);

        return i == 1;
    }
}
