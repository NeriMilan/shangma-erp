package com.shangma.service.afterSales.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.system.Permission;
import com.shangma.entity.system.Role;
import com.shangma.entity.system.User;
import com.shangma.mapper.afterSales.AfterSalesMapper;
import com.shangma.mapper.sale.b2c.OrderMapper;
import com.shangma.mapper.system.PermissionMapper;
import com.shangma.mapper.system.RoleMapper;
import com.shangma.mapper.system.UserMapper;
import com.shangma.service.afterSales.AfterSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AfterSalesServiceImpl implements AfterSalesService {

    @Autowired
    private AfterSalesMapper afterSalesMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<AfterSalesInformation> list() {
        return afterSalesMapper.selectList(null);
    }

    @Override
    public List<AfterSalesInformation> findAllByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        return afterSalesMapper.selectList(null);
    }

    @Override
    public AfterSalesInformation findById(Long id) {
        return afterSalesMapper.selectById(id);
    }

    @Override
    public List<AfterSalesInformation> findByProducer(String producer) {

        LambdaQueryWrapper<AfterSalesInformation> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<AfterSalesInformation> wrapper1 = wrapper.like(AfterSalesInformation::getProducer, producer);

        return afterSalesMapper.selectList(wrapper1);
    }

    @Override
    public List<AfterSalesInformation> findByProduceTime(long startTime, long endTime) {

        return afterSalesMapper.findByProduceTime(new Date(startTime),new Date(endTime));
    }

    @Override
    public int delByIds(List<Long> ids) {

        return afterSalesMapper.deleteBatchIds(ids);
    }

    @Override
    public int delById(Long id) {
        return afterSalesMapper.deleteById(id);
    }

    @Override
    public int update(AfterSalesInformation afterSalesInformation) {
        return afterSalesMapper.updateById(afterSalesInformation);
    }



    @Override
    public int examine(AfterSalesInformation afterSalesInformation) {
        afterSalesMapper.updateById(afterSalesInformation);
        return afterSalesMapper.updateById(afterSalesInformation);

    }


}
