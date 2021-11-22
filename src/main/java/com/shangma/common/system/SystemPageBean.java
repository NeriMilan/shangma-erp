package com.shangma.common.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageHelper;
import com.shangma.common.pagebean.PageBean;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-18 10:33
 * @Description :
 */
public class SystemPageBean {

    public static <T> PageBean<T> getPageBean(IService<T> iService, QueryWrapper<T> queryWrapper, Integer pageNum, Integer pageSize){

        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 3 : pageSize;

        PageHelper.startPage(pageNum, pageSize);
        List<T> list = iService.list(queryWrapper);
        PageBean<T> pageBean = PageBean.initData(list.size(), list);
        return pageBean;

    }



}
