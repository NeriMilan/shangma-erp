package com.shangma.controller.system;


import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.entity.sale.cart.ReceiveEntity;
import com.shangma.entity.system.Log;
import com.shangma.service.system.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @ClassName:LogController
 * @Description: TODO
 * @Author:
 * @Date:2021/11/23 10:42
 * @Version: JDK1.8
 */
@RestController
@RequestMapping("/system/logController/")
public class LogController {

    @Autowired
    private LogService logService;
    private Integer row;

    @GetMapping("query")
    public AxiosResult<PageInfo<Log>> query(@RequestBody ReceiveEntity receiveEntity){

        return AxiosResult.success(logService.queryLog(receiveEntity));
    }
    @DeleteMapping("remove/{logIds}")
    public AxiosResult remove(@PathVariable List<Long> logIds){

        row = logService.deleteBatchIds(logIds);
        return row>0?AxiosResult.success():AxiosResult.error();
    }
}
