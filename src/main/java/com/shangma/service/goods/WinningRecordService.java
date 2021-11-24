package com.shangma.service.goods;

import com.shangma.entity.goods.WinningRecord;
import com.shangma.service.goods.base.BaseService;

public interface WinningRecordService extends BaseService<WinningRecord> {
  Long lastId();
}
