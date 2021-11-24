package com.shangma.service.system.impl;

import com.shangma.entity.system.SystemLog;
import com.shangma.entity.system.vo.SystemLogVO;
import com.shangma.mapper.system.SystemLogMapper;
import com.shangma.service.system.SystemLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-24 10:05
 * @Description :
 */
@Service
@Slf4j
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public List<SystemLog> condition(SystemLogVO systemLogVO) {
        return systemLogMapper.condition(systemLogVO);
    }
}
