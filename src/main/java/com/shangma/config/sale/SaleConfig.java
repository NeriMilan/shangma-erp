package com.shangma.config.sale;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
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
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(){
        JedisPool jedisPool = new JedisPool(jedisPoolConfig(), "localhost", 6379);
        return jedisPool;
    }

}
