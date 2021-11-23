package com.shangma.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.system.Job;
import com.shangma.mapper.system.JobMapper;
import com.shangma.service.system.JobService;
import org.springframework.stereotype.Service;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:36
 * @Description :
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {
}
