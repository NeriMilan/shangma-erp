package com.shangma;

import com.shangma.entity.system.Operate;
import com.shangma.entity.system.Permission;
import com.shangma.entity.system.SystemLog;
import com.shangma.mapper.system.OperateMapper;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.mapper.system.SystemLogMapper;
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

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Test
    public void deleteNullLog(){
        //List<SystemLog> systemLogs = systemLogMapper.selectList(null);
        //for (SystemLog systemLog : systemLogs) {
        //    if (systemLog.getDescription() == null){
        //        systemLogMapper.deleteById(systemLog.getId());
        //    }
        //}
    }

    @Test
    public void injectDataForOperate(){

        List<Operate> operates = operateMapper.selectList(null);
        for (Operate operate : operates) {
            if(operate.getName()!=null){
                operate.setDescription(operate.getName());
                operateMapper.updateById(operate);
            }
        }

    }

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
