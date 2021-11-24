package com.shangma;

import com.shangma.entity.system.Operate;
import com.shangma.entity.system.Permission;
import com.shangma.mapper.system.OperateMapper;
import com.shangma.mapper.system.PermissionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-23 20:29
 * @Description :
 */
@SpringBootTest
public class SpringBootAppTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private OperateMapper operateMapper;

    @Test
    public void injectData(){

        List<Permission> permissionList = permissionMapper.selectList(null);
        List<Operate> operates = operateMapper.selectList(null);

        for (Permission permission : permissionList) {
            for (Operate operate : operates) {
                if (permission.getExt2() != null){
                    Long permission_operateId = Long.valueOf(permission.getExt2());
                    if (permission_operateId.equals(operate.getId())){
                        permission.setDescription(operate.getDescription());
                        permission.setExt1(operate.getDescription());
                        permission.setOperate(operate.getLocation());
                        permissionMapper.updateById(permission);
                    }
                }
            }
        }



    }

}
