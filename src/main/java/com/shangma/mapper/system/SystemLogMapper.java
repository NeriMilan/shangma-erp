package com.shangma.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.system.Operate;
import com.shangma.entity.system.SystemLog;
import com.shangma.entity.system.SystemLogExample;
import java.util.List;

import com.shangma.entity.system.vo.SystemLogVO;
import org.apache.ibatis.annotations.Param;

public interface SystemLogMapper  extends BaseMapper<SystemLog> {
    long countByExample(SystemLogExample example);

    int deleteByExample(SystemLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    List<SystemLog> selectByExample(SystemLogExample example);

    SystemLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemLog record, @Param("example") SystemLogExample example);

    int updateByExample(@Param("record") SystemLog record, @Param("example") SystemLogExample example);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);

    List<SystemLog> condition(SystemLogVO systemLogVO);
}