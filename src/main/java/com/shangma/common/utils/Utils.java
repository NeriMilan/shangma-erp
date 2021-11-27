package com.shangma.common.utils;

import com.shangma.common.http.AxiosResult;


public class Utils {

    /**
     *
     * @param condition controller层增加，修改，删除传回来的int值
     * @return 返回前端需要的AxiosResult
     */
    public static AxiosResult Judgement(Integer condition){
        if (condition > 0) {
            return AxiosResult.success();
        } else {
            return AxiosResult.error();
        }
    }
}
