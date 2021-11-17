package com.shangma.controller.sale.b2c;

import com.shangma.entity.sale.b2c.SalesOrder;
import com.shangma.service.sale.b2c.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CreateTime=2021/11/17
 */

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("findAll")
    public List<SalesOrder> findAll(){
        return orderService.findAll();
    }
}
