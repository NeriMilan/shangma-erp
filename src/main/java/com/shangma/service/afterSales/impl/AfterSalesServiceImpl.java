package com.shangma.service.afterSales.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.mapper.afterSales.AfterSalesMapper;
import com.shangma.service.afterSales.AfterSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AfterSalesServiceImpl implements AfterSalesService {

    @Autowired
    private AfterSalesMapper afterSalesMapper;

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


}
