package com.shangma.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shangma.entity.system.Permission;
import com.shangma.entity.system.User;
import com.shangma.entity.system.vo.PermissionVO;

import java.util.List;

public interface PermissionService {

    List<PermissionVO> condition(PermissionVO permissionVO);

    PermissionVO getById(Long id);

    boolean removeById(Long id, User master);

    boolean updateById(PermissionVO permissionVO, User master);

    boolean add(PermissionVO permissionVO, User master);
}
