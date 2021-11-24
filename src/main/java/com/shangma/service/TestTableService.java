package com.shangma.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.entity.TestTable;
import com.shangma.mapper.TestTableMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author : Ryze
 * @create : 2021-11-17 10:54
 * @Description :
 */
@Service
public class TestTableService {

    @Autowired
    private TestTableMapper testTableMapper;

    public PageInfo getAll(int page, int size) {
// 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(page, size);
        List<TestTable> list = testTableMapper.selectList(null);
// 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
        PageInfo<TestTable> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

}
