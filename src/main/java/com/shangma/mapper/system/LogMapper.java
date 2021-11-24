package com.shangma.mapper.system;

import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.entity.system.Log;
import com.shangma.mapper.base.MyMapper;

import java.util.List;

public interface LogMapper extends MyMapper<Log> {
    List<Log> selectLog(ReceiveEntity receiveEntity);
}
