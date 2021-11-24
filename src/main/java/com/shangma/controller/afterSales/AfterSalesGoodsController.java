package com.shangma.controller.afterSales;

import com.shangma.common.http.AxiosResult;
import com.shangma.entity.afterSales.AfterSalesGoodsInformation;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.entity.goods.Goods;
import com.shangma.service.afterSales.AfterSalesGoodsService;
import com.shangma.service.afterSales.AfterSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("afterSalesGoods")
public class AfterSalesGoodsController {
    @Autowired
    private AfterSalesGoodsService afterSalesGoodsService;
    @GetMapping("orderGoods")
    public AxiosResult orderGoods(Long id){
        /**
         * 1.查询出所有订单
         * 2.根据订单id查询出该订单下的商品
         * 3.根据商品id把商品信息添加到退货信息表中
         * 4.修改退货商品信息表的值
         */
        List<AfterSalesGoodsInformation> afterSalesGoods = afterSalesGoodsService.findById(id);
        if (afterSalesGoods!=null){
            return AxiosResult.success(afterSalesGoods);
        }else {
            return AxiosResult.error();
        }
    }
    @GetMapping("returnable")
    public AxiosResult returnable(@RequestBody AfterSalesGoodsInformation afterSalesGoodsInformation){
        int rows = afterSalesGoodsService.insert(afterSalesGoodsInformation);
        if (rows>0){
            return AxiosResult.success(afterSalesGoodsInformation);
        }
        return AxiosResult.error();
    }
    @PostMapping("update")
    public AxiosResult update(@RequestBody AfterSalesGoodsInformation afterSalesGoodsInformation){

        int rows = afterSalesGoodsService.update(afterSalesGoodsInformation);


        if (rows>0){
            //在售后信息表里新增一条数据
            AfterSalesInformation afterSalesInformation = new AfterSalesInformation();
            afterSalesInformation.setId(afterSalesGoodsInformation.getId());
            afterSalesInformation.setProducer("Admin");
            afterSalesInformation.setProduceTime(LocalDateTime.now());
            afterSalesInformation.setApproveStatus("0");
            int i = afterSalesGoodsService.insertAfterSales(afterSalesInformation);
            System.out.println(afterSalesInformation);
            System.out.println(i);
            return AxiosResult.success();
        }
        return AxiosResult.error();
    }




}
