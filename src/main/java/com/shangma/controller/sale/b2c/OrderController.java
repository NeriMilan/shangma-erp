package com.shangma.controller.sale.b2c;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.Customer;
import com.shangma.entity.sale.b2c.OrderGoods;
import com.shangma.entity.sale.b2c.SalesOrder;
import com.shangma.service.sale.b2c.OrderService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @CreateTime=2021/11/17
 *
 * salesOrderQueryWrapper
 *                 .like("order_id",salesOrder.getOrderId())
 *                 .like("order_type",salesOrder.getOrderType())
 *                 .like("payment",salesOrder.getPayment())
 *                 .like("business_type",salesOrder.getBusinessType())
 *                 .like("order_date",salesOrder.getOrderDate())
 *                 .like("order_action",salesOrder.getOrderAction())
 *                 .like("order_status",salesOrder.getOrderStatus())
 *         ;
 */

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private OrderService orderService;
    @Autowired
    private XSSFWorkbook workbook;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    //????????????,????????????????????????????????????
    @GetMapping("findAll")
    public AxiosResult findAll(@RequestParam(defaultValue = "1") Integer currentPage
                                ,@RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        List<SalesOrder> all = orderService.findAll();
        PageInfo<SalesOrder> pageInfo = new PageInfo<>(all);
        long total = pageInfo.getTotal();
        List<SalesOrder> list = pageInfo.getList();
        PageBean<SalesOrder> pageBean = PageBean.initData(total, list);
        AxiosResult<PageBean<SalesOrder>> result = AxiosResult.success(pageBean);
        return result;
    }
//    @GetMapping("searchTest")
//    public List<SalesOrder> searchTest(SalesOrder salesOrder){
//        System.out.println("OrderDate: "+salesOrder.getOrderDate());
//        System.out.println("TimeFrom: "+salesOrder.getTimeFrom());
//        System.out.println("TimeTo: "+salesOrder.getTimeTo());
//        QueryWrapper<SalesOrder> wrapper = new QueryWrapper<>();
//        wrapper.between("order_date",salesOrder.getTimeFrom(),salesOrder.getTimeTo());
//        List<SalesOrder> salesOrders = orderService.search(wrapper);
//        return salesOrders;
//    }
    // ??????????????????????????????
    @GetMapping("findBySearch")
    public AxiosResult findBySearch(SalesOrder salesOrder
                                        , @RequestParam(defaultValue = "1") Integer currentPage
                                        ,@RequestParam(defaultValue = "5") Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<SalesOrder> list = orderService.findBySearch(salesOrder);
        PageInfo<SalesOrder> pageInfo = new PageInfo<>(list);
        List<SalesOrder> orderList = pageInfo.getList();
        long total = pageInfo.getTotal();
        PageBean<SalesOrder> salesOrderPageBean = PageBean.initData(total, orderList);
        return AxiosResult.success(salesOrderPageBean);
    }

    // ????????????, ??????????????????id, ??????????????????????????????????????????????????????
    @GetMapping("findByOrder")
    public AxiosResult<SalesOrder> findByOrder(Long orderId){
        QueryWrapper<SalesOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        List<SalesOrder> list = orderService.search(queryWrapper);
        SalesOrder salesOrder = list.get(0);
        Long customerId = salesOrder.getCustomerId();
        // ??????????????????id???????????????????????????????????????salesOrder
        Customer customer = orderService.findCustomerById(customerId);
        salesOrder.setCustomer(customer);
        //????????????id????????????????????????????????????????????????????????????salesOrder
        List<OrderGoods> orderGoods = orderService.findOrderGoods(orderId);
        salesOrder.setGoods(orderGoods);
        return AxiosResult.success(salesOrder);
    }
    // ??????Excel????????????
    @RequestMapping("importExcel")
    public ResponseEntity<byte[]> importExcel(){
        List<SalesOrder> orderList = orderService.findAll();
        XSSFSheet sheet = workbook.createSheet("???????????????");
        List<String> strings = Arrays.asList("????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????","??????????????????");
        Class<SalesOrder> orderClass = SalesOrder.class;
        Field[] declaredFields = orderClass.getDeclaredFields();
        logger.debug("order??????,??????excel??????????????????"+declaredFields.length);
        XSSFRow row01 = sheet.createRow(0);
        for (int i=0; i<strings.size(); i++){
            XSSFCell cell = row01.createCell(i);
            cell.setCellValue(strings.get(i));
            logger.debug("order??????,??????excel???????????? ??? "+i+" ????????????: "+strings.get(i));
        }
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //localDateTime???????????? ????????????????????????????????????????????????
        //String dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(order.getOrderDate());
        for (int i = 0;i<orderList.size() ; i++){
            XSSFRow row = sheet.createRow(i+1);
            SalesOrder order = orderList.get(i);
            XSSFCell cell00 = row.createCell(0);
            cell00.setCellValue(order.getOrderId());
            XSSFCell cell01 = row.createCell(1);
            cell01.setCellValue(order.getOrderType());
            XSSFCell cell02 = row.createCell(2);
            cell02.setCellValue(order.getOrderAction());
            XSSFCell cell03 = row.createCell(3);
            cell03.setCellValue(order.getBusinessType());
            XSSFCell cell04 = row.createCell(4);
            cell04.setCellValue(order.getPayment());
            XSSFCell cell05 = row.createCell(5);
            cell05.setCellValue(order.getDistributionMode());
            XSSFCell cell06 = row.createCell(6);
            cell06.setCellValue(order.getOrderStatus());
            XSSFCell cell07 = row.createCell(7);
            cell07.setCellValue(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:dd").format(order.getOrderDate()));
            XSSFCell cell08 = row.createCell(8);
            cell08.setCellValue(order.getCustomerId());
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            workbook.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = stream.toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("???????????????.xlsx","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

}
