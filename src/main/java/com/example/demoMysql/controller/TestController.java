package com.example.demoMysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class TestController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getUsers")    //页面地址，当用户访问页面时就会触发getDbType方法。
    public List<Map<String, Object>> getDbType() {
        String sql = "select * from student";//执行SQL查询
        //mysql一行数据对应一个map，所以用List<Map<>>
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);//解析数据，到这里已经完成网页功能。
        //对于每一行数据，打印到本地。
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet();//一个map多条数据，转成set<key,val>的形式
            if (entries != null) {
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();//用迭代器访问
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key + ":" + value);
                }
            }
        }
        return list;
    }
}

