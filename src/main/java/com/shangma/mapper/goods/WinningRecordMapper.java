package com.shangma.mapper.goods;

import com.shangma.entity.goods.WinningRecord;
import com.shangma.mapper.base.MyMapper;

public interface WinningRecordMapper extends MyMapper<WinningRecord> {
    Long lastId();
}
