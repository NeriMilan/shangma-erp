package com.shangma.controller;

import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import com.shangma.service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 10:54
 * @Description :
 */
@Controller
public class TestTableController {

    @Autowired
    private TestTableService testTableService;

    @RequestMapping("/test")
    @ResponseBody
    public AxiosResult test(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int size){

        PageInfo pageInfo = testTableService.getAll(page, size);

        AxiosResult<PageInfo> success = AxiosResult.success(pageInfo);

        return success;
    }

}
