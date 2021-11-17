package com.shangma.service;

import com.shangma.mapper.TestTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List getAll(){
        return testTableMapper.selectList(null);
    }

}
