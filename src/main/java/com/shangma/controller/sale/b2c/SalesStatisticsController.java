package com.shangma.controller.sale.b2c;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.SalesOrder;
import com.shangma.entity.sale.b2c.SalesStatistics;
import com.shangma.service.sale.b2c.SalesStatisticsService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * @CreateTime: 2021/11/19  10:52
 */

@RestController
@RequestMapping("order")
public class SalesStatisticsController {

    @Autowired
    private SalesStatisticsService statisticsService;
    @Autowired
    private XSSFWorkbook workbook;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @GetMapping("getRank")
    public AxiosResult getRank(
             @RequestParam(defaultValue = "1") Integer currentPage
            ,@RequestParam(defaultValue = "5") Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        List<SalesStatistics> list = statisticsService.getRank();
        PageInfo<SalesStatistics> info = PageInfo.of(list);
        long total = info.getTotal();
        List<SalesStatistics> infoList = info.getList();
        PageBean<SalesStatistics> pageBean = PageBean.initData(total, infoList);
        return AxiosResult.success(pageBean);
    }

    @GetMapping("getRankBySearch")
    public AxiosResult getRankBySearch(
             @RequestParam(defaultValue = "1") Integer currentPage
            ,@RequestParam(defaultValue = "5") Integer pageSize
            ,SalesStatistics salesStatistics){
        PageHelper.startPage(currentPage,pageSize);
        List<SalesStatistics> list = statisticsService.getRankBySearch(salesStatistics);
        PageInfo<SalesStatistics> pageInfo = PageInfo.of(list);
        long total = pageInfo.getTotal();
        List<SalesStatistics> pageInfoList = pageInfo.getList();
        PageBean<SalesStatistics> pageBean = PageBean.initData(total, pageInfoList);
        return AxiosResult.success(pageBean);
    }
    @RequestMapping("importRankOrder")
    public ResponseEntity<byte[]> importRankOrder(){
        List<SalesStatistics> orderList = statisticsService.getRank();
        XSSFSheet sheet = workbook.createSheet("销售排行表");
        List<String> strings = Arrays.asList("商品名称", "商品分类", "商品品牌", "商品型号", "商品颜色", "商品销量");
        XSSFRow row01 = sheet.createRow(0);
        for (int i=0; i<strings.size(); i++){
            XSSFCell cell = row01.createCell(i);
            cell.setCellValue(strings.get(i));
            logger.debug("order模块,导出excel的第一排 第 "+i+" 个字段为: "+strings.get(i));
        }
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //localDateTime时间类型 使用下面这种转换方式转换成字符串
        //String dateStr = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(order.getOrderDate());
        for (int i = 0;i<orderList.size() ; i++){
            XSSFRow row = sheet.createRow(i+1);
            SalesStatistics statistics = orderList.get(i);
            XSSFCell cell00 = row.createCell(0);
            cell00.setCellValue(statistics.getGoodName());
            XSSFCell cell01 = row.createCell(1);
            cell01.setCellValue(statistics.getTypeName());
            XSSFCell cell02 = row.createCell(2);
            cell02.setCellValue(statistics.getBrandName());
            XSSFCell cell03 = row.createCell(3);
            cell03.setCellValue(statistics.getGoodsModel());
            XSSFCell cell04 = row.createCell(4);
            cell04.setCellValue(statistics.getGoodsColor());
            XSSFCell cell05 = row.createCell(5);
            cell05.setCellValue(statistics.getTotalCount());
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
            httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("销售排行表.xlsx","utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }
}
