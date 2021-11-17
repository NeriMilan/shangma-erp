package com.shangma.service.system.impl;

//import com.shangma.entity.system.User;
//import com.shangma.mapper.system.UserMapper;
import com.shangma.entity.system.User;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> list(){
        return userMapper.selectByExample(null);
    }

}
