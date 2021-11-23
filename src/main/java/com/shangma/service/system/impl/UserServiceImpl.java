package com.shangma.service.system.impl;

//import com.shangma.entity.system.User;
//import com.shangma.mapper.system.UserMapper;
import com.shangma.controller.system.UserController;
import com.shangma.entity.system.User;
import com.shangma.entity.system.UserExample;
import com.shangma.mapper.system.UserMapper;
import com.shangma.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 17:08
 * @Description :
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        List<User> list = userMapper.selectList(null);
        return list;
    }

    @Override
    public List<User> condition(User user){
        return userMapper.condition(user);
    }

    @Override
    public User getById(Long id){
        return userMapper.selectById(id);
    }

    @Override
    public boolean removeById(Long id, User master) {
        flushUpdate(id, master);
        return userMapper.deleteByPrimaryKey(id) == 1 ? true : false;
    }

    @Override
    public boolean updateById(User user, User master) {
        flushUpdate(user.getId(), master);
        return userMapper.updateById(user)  == 1 ? true : false;
    }

    @Override
    public boolean add(User user, User master) {
        user.setCreateId(master.getId());
        user.setUpdateId(master.getId());

        return userMapper.insertSelective(user) == 1 ? true : false;
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
        return userMapper.updateById(user) == 1 ? true : false;
    }

}
