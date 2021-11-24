package com.shangma.service.system.impl;

//import com.shangma.entity.system.User;
//import com.shangma.mapper.system.UserMapper;

import com.shangma.common.system.SystemReturnTool;
import com.shangma.controller.system.UserController;
import com.shangma.entity.system.*;
import com.shangma.entity.system.vo.LoginUserVO;
import com.shangma.entity.system.vo.tool.*;
import com.shangma.mapper.system.OperateMapper;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.mapper.system.RolePermissionMapper;
import com.shangma.mapper.system.UserMapper;
import com.shangma.service.system.UserService;
import org.apache.commons.collections4.list.TreeList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author : Ryze
 * @create : 2021-11-17 17:08
 * @Description :
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private OperateMapper operateMapper;

    @Override
    public List<User> list() {
        List<User> list = userMapper.selectList(null);
        return list;
    }

    @Override
    public List<User> condition(User user) {
        return userMapper.condition(user);
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean removeById(Long id, User master) {
        flushUpdate(id, master);
        int i = userMapper.deleteByPrimaryKey(id);
        return SystemReturnTool.getReturnResult(i);
    }

    @Override
    public boolean updateById(User user, User master) {
        flushUpdate(user.getId(), master);
        int i = userMapper.updateById(user);
        return SystemReturnTool.getReturnResult(i);
    }

    @Override
    public boolean add(User user, User master) {
        user.setCreateId(master.getId());
        user.setUpdateId(master.getId());
        int i = userMapper.insertSelective(user);
        return SystemReturnTool.getReturnResult(i);
    }

    @Override
    public List<User> listByLoginNameAndPassword(String loginName, String password) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public boolean flushUpdate(Long id, User master) {
        User user = userMapper.selectById(id);
        user.setUpdateId(master.getId());
        int i = userMapper.updateById(user);
        return SystemReturnTool.getReturnResult(i);
    }

  /*  @Override
    public LoginUserVO getLoginUserByUser(User loginUser) {

        List<Operate> operates = new ArrayList<>();
        LoginUserVO loginUserVO = new LoginUserVO(
                loginUser.getId(),
                loginUser.getLoginName(),
                loginUser.getUsername(),
                loginUser.getDepartmentId(),
                loginUser.getSex(),
                loginUser.getStatus(),
                loginUser.getJobId(),
                loginUser.getPhone(),
                loginUser.getEmail(),
                loginUser.getIp(),
                loginUser.getRoleId(),
                operates
        );

        List<Operate> dbOperates = operateMapper.selectList(null);
        Set<Operate> targetOperate = new TreeSet<>();

        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andRoleIdEqualTo(loginUserVO.getRoleId());
        for (RolePermission rolePermission : rolePermissionMapper.selectByExample(rolePermissionExample)) {
            Permission permission = permissionMapper.selectByPrimaryKey(rolePermission.getPermissionId());
            Operate operate = operateMapper.selectByPrimaryKey(Long.valueOf(permission.getExt2()));

            targetOperate.add(operate);
        }

        return loginUserVO;
    }*/



}
