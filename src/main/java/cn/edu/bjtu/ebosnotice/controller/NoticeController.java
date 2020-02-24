package cn.edu.bjtu.ebosnotice.controller;

import cn.edu.bjtu.ebosnotice.service.Log;
import cn.edu.bjtu.ebosnotice.service.LogFind;
import cn.edu.bjtu.ebosnotice.service.MqConsumer;
import cn.edu.bjtu.ebosnotice.service.MqFactory;
import cn.edu.bjtu.ebosnotice.service.impl.LogFindImpl;
import cn.edu.bjtu.ebosnotice.service.log.LogImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/notice")
@RestController
public class NoticeController {
    @Autowired
    MqFactory mqFactory;
    @Autowired
    Log log = new LogImpl();
    @Autowired
    LogFind logFind = new LogFindImpl();

    @CrossOrigin
    @GetMapping("/alert")
    public String getAlert() {
        MqConsumer mqConsumer = mqFactory.createConsumer("notice");
        while (true) {
            JSONObject msg = JSON.parseObject(mqConsumer.subscribe());
            String alert = msg.getString("content");
            return alert;

        }
    }

    @CrossOrigin
    @RequestMapping ("/logtest")
    public String logtest1(){
        log.debug("notice");
        return "成功";
    }
    @CrossOrigin
    @GetMapping("/logtest")
    public String logtest2(){
        return logFind.read("level","DEBUG");
    }
}
