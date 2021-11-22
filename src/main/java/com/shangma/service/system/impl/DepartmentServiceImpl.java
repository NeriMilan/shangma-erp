package com.shangma.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shangma.entity.system.Department;
import com.shangma.mapper.system.DepartmentMapper;
import com.shangma.service.system.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * @author : Ryze
 * @create : 2021-11-17 20:27
 * @Description :
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
}
