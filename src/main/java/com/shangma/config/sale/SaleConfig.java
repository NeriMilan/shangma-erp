package com.shangma.config.sale;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @CreateTime: 2021/11/18  19:39
 */
@Configuration
public class SaleConfig {

    @Bean
    public XSSFWorkbook xssfWorkbook(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        return workbook;
    }
}
