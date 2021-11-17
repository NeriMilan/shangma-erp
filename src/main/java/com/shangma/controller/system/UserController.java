package com.shangma.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.system.User;
import com.shangma.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 17:09
 * @Description :
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public AxiosResult getAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "1") int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.list();
        PageInfo<User> info = new PageInfo<>(list);
        PageBean<User> data = PageBean.initData(info.getSize(), info.getList());
        return AxiosResult.success(data);
    }
}
