package com.shangma.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shangma.entity.system.Job;
import com.shangma.entity.system.JobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobMapper extends BaseMapper<Job> {
    long countByExample(JobExample example);

    int deleteByExample(JobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Job record);

    int insertSelective(Job record);

    List<Job> selectByExample(JobExample example);

    Job selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Job record, @Param("example") JobExample example);

    int updateByExample(@Param("record") Job record, @Param("example") JobExample example);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}