package com.shangma.controller.afterSales;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.service.afterSales.AfterSalesService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("afterSales")
public class AfterSalesController {
    @Autowired
    private AfterSalesService afterSalesService;

    @GetMapping("findAll")
    public AxiosResult findAll(@RequestParam(defaultValue = "1") Integer pageIndex,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<AfterSalesInformation> afterSalesInformations = afterSalesService.list();
        PageInfo<AfterSalesInformation> pageInfo = new PageInfo<>(afterSalesInformations);
        long total = pageInfo.getTotal();
        List<AfterSalesInformation> list = pageInfo.getList();
        PageBean<AfterSalesInformation> pageBean = PageBean.initData(total, list);
        if (pageBean != null){
            return AxiosResult.success(pageBean);
        }else {
            return AxiosResult.error();
        }
    }

    @GetMapping("findById")
    public AxiosResult<AfterSalesInformation> findById(Long id) {
        AfterSalesInformation afterSalesInformation = afterSalesService.findById(id);
        if (afterSalesInformation!=null){
            return AxiosResult.success(afterSalesInformation);
        }else {
            return AxiosResult.error();
        }

    }

    @GetMapping("findByProducer")
    public AxiosResult findByProducer(String producer,@RequestParam(defaultValue = "1") Integer pageIndex,
                                                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<AfterSalesInformation> afterSalesInformations = afterSalesService.findByProducer(producer);
        PageInfo<AfterSalesInformation> pageInfo = new PageInfo<>(afterSalesInformations);
        long total = pageInfo.getTotal();
        List<AfterSalesInformation> list = pageInfo.getList();
        PageBean<AfterSalesInformation> pageBean = PageBean.initData(total, list);
        if (pageBean!=null){
            return AxiosResult.success(pageBean);
        }else {
            return AxiosResult.error();
        }
    }
    @GetMapping("findByProduceTime")
    public AxiosResult findByProduceTime(@RequestParam(value = "startTime",required = false) long startTime,
                                                                      @RequestParam(value = "endTime",required = false) long endTime,
                                         @RequestParam(defaultValue = "1") Integer pageIndex,@RequestParam(defaultValue = "10") Integer pageSize
    ) {
        PageHelper.startPage(pageIndex,pageSize);
        List<AfterSalesInformation> afterSalesInformations = afterSalesService.findByProduceTime(startTime, endTime);
        PageInfo<AfterSalesInformation> pageInfo = new PageInfo<>(afterSalesInformations);
        long total = pageInfo.getTotal();
        List<AfterSalesInformation> list = pageInfo.getList();
        PageBean<AfterSalesInformation> data = PageBean.initData(total, list);
        if (data!=null){
            return AxiosResult.success(data);
        }else {
            return AxiosResult.error();
        }
    }
    @DeleteMapping("batch/{ids}")
    public AxiosResult delByIds(@PathVariable List<Long> ids){
        int rows = afterSalesService.delByIds(ids);
        if (rows>0){
            return AxiosResult.success();
        }else {
            return AxiosResult.error();
        }
    }
    @DeleteMapping("{id}")
    public AxiosResult delById(Long id){
        int row = afterSalesService.delById(id);
        if (row>0){
            return AxiosResult.success();
        }else {
            return AxiosResult.error();
        }
    }
    @PutMapping()
    public AxiosResult update(@RequestBody AfterSalesInformation afterSalesInformation){
        int row = afterSalesService.update(afterSalesInformation);
        if (row>0){
            return AxiosResult.success();
        }else {
            return AxiosResult.error();
        }
    }
    @GetMapping("writeExcel")
    public ResponseEntity<byte[]> writeExcel() throws IOException {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        List<AfterSalesInformation> afterSalesInformations = afterSalesService.list();

        //创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("售后信息表");
        Row titleRow = sheet.createRow(0);
        //把数据库列名放入表头
        List<String> strings = Arrays.asList("销售订单编号","制单人","制单时间","审批人",
                "审批时间","审批状态");
        for (int i = 0; i < strings.size(); i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(strings.get(i));
        }
        //把数据库数据放进创建的Excel
        for (int i = 0; i < afterSalesInformations.size(); i++) {
            Row row = sheet.createRow(i + 1);
            AfterSalesInformation afterSalesInformation = afterSalesInformations.get(i);
            Cell idCell = row.createCell(0);
            idCell.setCellValue(afterSalesInformation.getId());
            Cell producerCell = row.createCell(1);
            producerCell.setCellValue(afterSalesInformation.getProducer());
            Cell produceTimeCell = row.createCell(2);
            produceTimeCell.setCellValue(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(afterSalesInformation.getProduceTime()));
            Cell approverCell = row.createCell(3);
            approverCell.setCellValue(afterSalesInformation.getApprover());
            Cell approverTimeCell = row.createCell(4);
            approverTimeCell.setCellValue(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(afterSalesInformation.getApproverTime()));
            Cell approveStatusCell = row.createCell(5);

            Integer status =  Integer.parseInt(afterSalesInformation.getApproveStatus());
            String temp =status==0?"未审核":
                    (status==1?"已审核":"审核未通过");
            approveStatusCell.setCellValue(temp);
        }
        //把Excel文件写进内存
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        byte[] bytes = stream.toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("售后信息表.xlsx","utf-8"));
        //最后以文件下载的方式导出
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("returnable")
    public AxiosResult returnable(){
        /**
         * 1.查询出所有订单
         * 2.根据订单id查询出该订单下的商品
         * 3.根据商品id把商品信息添加到退货信息表中
         * 4.修改退货商品信息表的值
         */




        return AxiosResult.success();
    }

}