package com.shangma.common.system;

import com.shangma.common.http.AxiosStatus;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author : Ryze
 * @create : 2021-11-23 22:50
 * @Description :
 */
public class SystemReturnTool {

    public static boolean getReturnResult(int i){
        if(i >= 0)
            return true;

        throw new MySystemException(AxiosStatus.ERROR_DATABASE_OPTIONAL.getMessage());
    }


}
