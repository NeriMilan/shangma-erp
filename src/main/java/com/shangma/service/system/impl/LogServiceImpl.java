package com.shangma.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.entity.system.Log;
import com.shangma.mapper.system.LogMapper;
import com.shangma.service.sale.b2c.base.impl.BaseServiceImpl;
import com.shangma.service.system.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:LogServiceImpl
 * @Description: TODO
 * @Author:
 * @Date:2021/11/23 10:39
 * @Version: JDK1.8
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public PageInfo<Log> queryLog(ReceiveEntity receiveEntity) {
        PageHelper.startPage(receiveEntity.getPageNum(),receiveEntity.getPageSize());
        List<Log> list = logMapper.selectLog(receiveEntity);
        PageInfo<Log> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
}
