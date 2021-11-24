package com.shangma.controller.afterSales;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.afterSales.EnterMareHouse;
import com.shangma.entity.afterSales.EnterMareHouseGoods;
import com.shangma.entity.system.User;
import com.shangma.service.afterSales.EnterMareHouseGoodsService;
import com.shangma.service.afterSales.EnterMareHouseService;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("enterMareHouse")
public class EnterMareHouseController {
    @Autowired
    private EnterMareHouseService enterMareHouseService;

    @Autowired
    private EnterMareHouseGoodsService enterMareHouseGoodsService;

    /**
     * 动态查询&分页
     */
    @GetMapping("select")
    public AxiosResult selectBySearch(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            EnterMareHouse enterMareHouse) {
        PageHelper.startPage(pageIndex, pageSize);
        List<EnterMareHouse> enterMareHouses = enterMareHouseService.selectBySearch(enterMareHouse);
        PageInfo<EnterMareHouse> pageInfo = new PageInfo<>(enterMareHouses);
        long total = pageInfo.getTotal();
        List<EnterMareHouse> list = pageInfo.getList();
        PageBean<EnterMareHouse> data = PageBean.initData(total, list);
        if (data != null) {
            return AxiosResult.success(data);
        }

        return AxiosResult.error();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("batch/{ids}")
    public AxiosResult delByids(List<Long> ids) {
        int row = enterMareHouseService.delByids(ids);
        if (row > 0) {
            return AxiosResult.success();
        }
        return AxiosResult.error();
    }

    /**
     * 导出
     *
     * @return
     * @throws IOException
     */
    @GetMapping("writeExcel")
    public AxiosResult writeExcel() throws IOException {
        List<EnterMareHouse> enterMareHouses = enterMareHouseService.selectBySearch(null);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("返厂入库表");
        Row titleRow = sheet.createRow(0);
        List<String> strings = Arrays.asList("返厂入库单号", "返厂入库标志", "制单人", "制单时间", "审批人", "审批状态");
        for (int i = 0; i < strings.size(); i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(strings.get(i));
        }
        for (int i = 0; i < enterMareHouses.size(); i++) {

            Row row = sheet.createRow(i + 1);
            EnterMareHouse enterMareHouse = enterMareHouses.get(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(enterMareHouse.getId());
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(enterMareHouse.getSign());
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(enterMareHouse.getProducer());
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(enterMareHouse.getProduceTime()));
            Cell cell4 = row.createCell(4);
            cell4.setCellValue(enterMareHouse.getApprover());
            Cell cell5 = row.createCell(5);
            int status = Integer.parseInt(enterMareHouse.getApproverStatus());
            String temp = status == 0 ? "未审核" : (status == 1 ? "已审核" : "审核未通过");
            cell5.setCellValue(temp);
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        byte[] bytes = stream.toByteArray();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode("返厂入库表.xlsx", "utf-8"));
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        return AxiosResult.success(responseEntity);
    }


    /**
     * 新增返厂商品
     */
    @GetMapping("findById")
    public AxiosResult findById(EnterMareHouseGoods enterMareHouseGoods) {
        EnterMareHouseGoods orderGoods = enterMareHouseGoodsService.findOrderGoods(enterMareHouseGoods);
        if (orderGoods != null) {
            System.out.println("========");
            orderGoods.setGoodsTrace("B2C销售售后");
            enterMareHouseGoodsService.insert(orderGoods);
            EnterMareHouseGoods data = enterMareHouseGoodsService.findOrderGoods(orderGoods);
            return AxiosResult.success(data);
        }
        return AxiosResult.error();
    }

    @GetMapping("update")
    public AxiosResult update(HttpServletRequest httpServletRequest,@RequestBody EnterMareHouseGoods enterMareHouseGoods) {
        int row = enterMareHouseGoodsService.update(enterMareHouseGoods);
        if (row > 0) {

            EnterMareHouse enterMareHouse = new EnterMareHouse();
            enterMareHouse.setId(enterMareHouseGoods.getGoodId());
            HttpSession session = httpServletRequest.getSession();
            User user = (User) session.getAttribute("user");
            enterMareHouse.setProducer(user.getLoginName());
            enterMareHouse.setProduceTime(LocalDateTime.now());
            enterMareHouseService.insert(enterMareHouse);
            return AxiosResult.success(enterMareHouseGoods);
        }
        return AxiosResult.error();
    }

    @GetMapping("selectById")
    public AxiosResult selectById(Long goodId) {
        EnterMareHouseGoods byId = enterMareHouseGoodsService.findById(goodId);
        if (byId!=null){
            return AxiosResult.success(byId);
        }
        return AxiosResult.error();
    }



    }
