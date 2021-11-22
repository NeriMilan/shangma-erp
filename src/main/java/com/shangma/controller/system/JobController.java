package com.shangma.controller.system;

import com.shangma.common.http.AxiosResult;
import com.shangma.entity.system.Department;
import com.shangma.entity.system.Job;
import com.shangma.service.system.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Ryze
 * @create : 2021-11-17 21:22
 * @Description :
 */
@RestController
@RequestMapping("/system/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("/list")
    public AxiosResult list(){
        List<Job> list = jobService.list();

        return AxiosResult.success(list);
    }

}
