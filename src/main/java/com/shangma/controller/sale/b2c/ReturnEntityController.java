package com.shangma.controller.sale.b2c;

import com.shangma.common.http.AxiosResult;
import com.shangma.common.pagebean.PageBean;
import com.shangma.entity.sale.b2c.ReturnEntityManage;
import com.shangma.entity.system.User;
import com.shangma.service.sale.b2c.ReturnEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @CreateTime: 2021/11/24  14:32
 */
@RestController
@RequestMapping("returnEntity")
public class ReturnEntityController {
    @Autowired
    private ReturnEntityService returnEntityService;
    @GetMapping("findAll")
    public AxiosResult findAll(
            @RequestParam(defaultValue = "1") Integer currentPage,
            @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = returnEntityService.findAllOrder(currentPage, pageSize);
        return AxiosResult.success(pageBean);
    }
    @GetMapping("search")
    public AxiosResult search(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              ReturnEntityManage returnEntityManage){
        PageBean pageBean = returnEntityService.Search(currentPage, pageSize, returnEntityManage);
        return AxiosResult.success(pageBean);
    }
    @GetMapping("getOrderInfo")
    public AxiosResult getOrderInfo(Long orderId){
        ReturnEntityManage orderInfo = returnEntityService.getOrderInfo(orderId);
        return AxiosResult.success(orderInfo);
    }

    /**
     * 参数需要 订单id 退货原因 退货时间
     */
    @PostMapping("generateRecord")
    public AxiosResult generateRecord(@RequestBody ReturnEntityManage returnEntityManage, HttpServletRequest request){
        String creator = "";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            creator = "未知" ;
        }else {
            creator = user.getUsername();
        }
        returnEntityManage.setCreator(creator);
        returnEntityService.generateRecord(returnEntityManage);
        return AxiosResult.success();
    }
}
