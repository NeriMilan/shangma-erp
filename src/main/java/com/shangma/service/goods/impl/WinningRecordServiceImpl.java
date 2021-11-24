package com.shangma.service.goods.impl;

import com.shangma.entity.goods.WinningRecord;
import com.shangma.mapper.goods.WinningRecordMapper;
import com.shangma.service.goods.WinningRecordService;
import com.shangma.service.goods.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class WinningRecordServiceImpl extends BaseServiceImpl<WinningRecord>implements WinningRecordService {
    @Autowired
    private WinningRecordMapper mapper;
    @Override
    public Long lastId() {
        return mapper.lastId() ;
    }
}
