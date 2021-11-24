package com.shangma.service.system;

import com.github.pagehelper.PageInfo;
import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.entity.system.Log;
import com.shangma.service.sale.b2c.base.BaseService;



public interface LogService extends BaseService<Log> {
    PageInfo<Log> queryLog(ReceiveEntity receiveEntity);
}
