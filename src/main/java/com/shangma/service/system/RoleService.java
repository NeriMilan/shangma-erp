package com.shangma.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shangma.entity.system.Role;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.RoleVO;

import java.util.List;

public interface RoleService {
    List<Role> list();

    List<RoleVO> condition(RoleVO roleVO);

    RoleVO getById(Long id, boolean isVO);

    boolean removeById(Long id, User master);

    //boolean flushUpdate(RoleVO roleVO, User user);

    boolean updateById(RoleVO roleVO, User master);

    boolean add(RoleVO roleVO, User master);
}
