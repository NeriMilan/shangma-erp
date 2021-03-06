package com.shangma.controller.afterSales;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.afterSales.AfterSalesInformation;
import com.shangma.entity.system.User;
import com.shangma.service.afterSales.AfterSalesService;
import lombok.experimental.Helper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

        //?????????????????????
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("???????????????");
        Row titleRow = sheet.createRow(0);
        //??????????????????????????????
        List<String> strings = Arrays.asList("??????????????????","?????????","????????????","?????????",
                "????????????","????????????");
        for (int i = 0; i < strings.size(); i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(strings.get(i));
        }
        //?????????????????????????????????Excel
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
            String temp =status==0?"?????????":
                    (status==1?"?????????":"???????????????");
            approveStatusCell.setCellValue(temp);
        }
        //???Excel??????????????????
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        byte[] bytes = stream.toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("???????????????.xlsx","utf-8"));
        //????????????????????????????????????
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }
    /**
     * ??????
     * ??????????????????????????? ????????????????????????(???????????????)
     */
    @PostMapping("examine")
    public AxiosResult examine(HttpServletRequest httpRequest,@RequestBody AfterSalesInformation afterSalesInformation,String status){
        User user = null;
        HttpSession session = httpRequest.getSession();
        Object obj = session.getAttribute("user");
        if (obj!=null){
            user = (User) obj;
        }

        afterSalesInformation.setApproverTime(LocalDateTime.now());
        afterSalesInformation.setApprover(user.getLoginName());
        afterSalesInformation.setApproveStatus(status);

        int row = afterSalesService.update(afterSalesInformation);
        if (row>0){
            return AxiosResult.success();
        }
            return AxiosResult.error();

    }


    /**
     * ???????????????
     *
     */
    @GetMapping("selectBySearch")
    public AxiosResult selectBySearch(
            @RequestParam(defaultValue = "1")Integer pageIndex,
            @RequestParam(defaultValue = "10")Integer pageSize,
            AfterSalesInformation afterSalesInformation){
        PageHelper.startPage(pageIndex,pageSize);
        List<AfterSalesInformation> afterSalesInformations = afterSalesService.selectBySearch(afterSalesInformation);
        PageInfo<AfterSalesInformation> pageInfo = new PageInfo<>(afterSalesInformations);
        //???????????????
        long total = pageInfo.getTotal();
        List<AfterSalesInformation> list = pageInfo.getList();
        PageBean<AfterSalesInformation> data = PageBean.initData(total, list);
        if (data!=null){
            return AxiosResult.success(data);
        }
        return AxiosResult.error();

    }




}