package com.shangma.service.goods.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.entity.goods.GoodsBrand;
import com.shangma.mapper.goods.GoodsBrandMapper;
import com.shangma.service.goods.GoodsBrandService;
import com.shangma.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: SMH
 * @time: 2021/11/17 16:11
 */
@Service
public class GoodsBrandServiceImpl extends ServiceImpl<GoodsBrandMapper,GoodsBrand> implements GoodsBrandService {

}
