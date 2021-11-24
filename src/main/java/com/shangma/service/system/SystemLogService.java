package com.shangma.service.system;

import com.shangma.entity.system.SystemLog;
import com.shangma.entity.system.vo.SystemLogVO;

import java.util.List;

public interface SystemLogService {
    List<SystemLog> condition(SystemLogVO systemLogVO);
}
