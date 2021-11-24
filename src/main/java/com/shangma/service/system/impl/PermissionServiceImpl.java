package com.shangma.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.common.system.SystemReturnTool;
import com.shangma.entity.system.*;
import com.shangma.entity.system.vo.PermissionVO;
import com.shangma.entity.system.vo.ReportVO;
import com.shangma.entity.system.vo.UserRolePermissionVO;
import com.shangma.mapper.system.OperateMapper;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.mapper.system.RolePermissionMapper;
import com.shangma.service.system.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:38
 * @Description :
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private OperateMapper operateMapper;

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

        return SystemReturnTool.getReturnResult(i);
    }

    @Override
    public boolean updateById(PermissionVO permissionVO, User master) {

        permissionVO.setUpdateId(master.getId());
        Operate operate = operateMapper.selectById(permissionVO.getExt2());
        permissionVO.setOperate(operate.getLocation());
        permissionVO.setExt1(operate.getDescription());

        int i = permissionMapper.updateById(permissionVO);

        return SystemReturnTool.getReturnResult(i);
    }

    @Override
    public boolean add(PermissionVO permissionVO, User master) {

        permissionVO.setCreateId(master.getId());
        permissionVO.setUpdateId(master.getId());

        Operate operate = operateMapper.selectById(permissionVO.getExt2());
        permissionVO.setOperate(operate.getLocation());
        permissionVO.setExt1(operate.getDescription());

        int i = permissionMapper.insertSelective(permissionVO);

        return SystemReturnTool.getReturnResult(i);
    }

    @Override
    public List<UserRolePermissionVO> conditionReport(User user) {
        return permissionMapper.conditionReport(user);
    }

    @Override
    public List<ReportVO> getDownloadRows(List<UserRolePermissionVO> userRolePermissionVOList) {

        List<ReportVO> rows = new ArrayList<>(userRolePermissionVOList.size());
        for (UserRolePermissionVO temp : userRolePermissionVOList) {
            rows.add(new ReportVO(
                    temp.getId(),
                    temp.getLoginName(),
                    temp.getUsername(),
                    temp.getDepartmentName(),
                    temp.getSex().equals(0L) ? "男" : "女",
                    temp.getStatus().equals(0) ? "无效" : "有效",
                    temp.getJobName(),
                    temp.getRoleName(),
                    StringUtils.join(temp.getPermissionNameList(), ","),
                    StringUtils.join(temp.getScopeList(), ",")
            ));
        }

        return rows;
    }
}
