package com.shangma.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.common.system.SystemReturnTool;
import com.shangma.entity.system.*;
import com.shangma.entity.system.vo.RoleVO;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.mapper.system.RoleMapper;
import com.shangma.mapper.system.RolePermissionMapper;
import com.shangma.service.system.RolePermissionService;
import com.shangma.service.system.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:40
 * @Description :
 */
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> list() {
        List<Role> list = roleMapper.selectList(null);
        return list;
    }

    @Override
    public List<RoleVO> condition(RoleVO roleVO) {

        return roleMapper.condition(roleVO);
    }

    @Override
    public RoleVO getById(Long id, boolean isVO) {

        RoleVO roleVo = roleMapper.selectVoById(id);
        return roleVo;
    }

    @Override
    public boolean removeById(Long id, User master) {

        RoleVO roleVO = getById(id, true);
        roleVO.setUpdateId(master.getId());
        roleMapper.updateById(roleVO);
        int i = roleMapper.deleteById(id);

        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleVO.getId());
        for (RolePermission rolePermission : rolePermissionMapper.selectByExample(rolePermissionExample)) {
            rolePermission.setUpdateId(master.getId());
            rolePermissionMapper.updateById(rolePermission);
        }
        rolePermissionMapper.deleteByExample(rolePermissionExample);

        return SystemReturnTool.getReturnResult(i);
    }

    //@Override
    //public boolean flushUpdate(RoleVO roleVO, User master) {
    //    roleVO.setUpdateId(master.getId());
    //    roleVO.setUpdateTime(LocalDateTime.now());
    //
    //    int i = roleMapper.updateByPrimaryKey(roleVO);
    //    return i == 1 ? true : false;
    //}

    @Override
    public boolean updateById(RoleVO roleVO, User master) {

        roleVO.setUpdateId(master.getId());
        int i = roleMapper.updateById(roleVO);

        //处理角色-权限和角色表
        //DB.r-p 对比 roleVO.r-p (  Permission_id ) (不变相同的, 删除减少的, 增加新增的)

        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleVO.getId());
        List<RolePermission> dBRolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);

        List<Permission> newPermissionList = roleVO.getPermissionList() == null ? new ArrayList<Permission>() : roleVO.getPermissionList();

        Iterator<RolePermission> dBIterator = dBRolePermissions.iterator();
        while ( dBIterator.hasNext() ){
            RolePermission dBRolePermission = dBIterator.next();

            Iterator<Permission> newIterator = newPermissionList.iterator();
            while ( newIterator.hasNext() ){
                Permission newPermission = newIterator.next();

                if (dBRolePermission.getPermissionId().equals(newPermission.getId())){
                    dBIterator.remove();
                    newIterator.remove();
                }
            }
        }

        for (RolePermission dBRolePermission : dBRolePermissions) {
            dBRolePermission.setUpdateId(master.getId());
            rolePermissionMapper.updateById(dBRolePermission);
            rolePermissionMapper.deleteByPrimaryKey(dBRolePermission.getId());
        }

        for (Permission permission : newPermissionList) {
            rolePermissionMapper.insertSelective(new RolePermission(null, roleVO.getId(), permission.getId(), null, master.getId(), null, master.getId(), null, null));
        }

        return SystemReturnTool.getReturnResult(i);
    }

    @Override
    public boolean add(RoleVO roleVO, User master) {

        log.info("测试返回主键开始 :{}",roleVO.getId());
        roleVO.setCreateId(master.getId());
        roleVO.setUpdateId(master.getId());
        // 自动填充主键
        int i = roleMapper.insertSelective(roleVO);
        log.info("测试返回主键结束 :{}",roleVO.getId());

        for (Permission permission : roleVO.getPermissionList()) {

            permission.setCreateId(master.getId());
            permission.setUpdateId(master.getId());

            rolePermissionMapper.insertSelective(new RolePermission( null, roleVO.getId(), permission.getId(), null, master.getId(), null, master.getId(),null, null));

        }

        return SystemReturnTool.getReturnResult(i);
    }

}
