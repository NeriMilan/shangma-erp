package com.shangma.controller;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.http.AxiosStatus;
import com.shangma.service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public AxiosResult test(){
        List list = testTableService.getAll();

        AxiosResult<List> success = AxiosResult.success(list);

        return success;
    }

}
