package cn.edu.bjtu.ebosnotice.controller;

import cn.edu.bjtu.ebosnotice.service.LogService;
import cn.edu.bjtu.ebosnotice.service.MqConsumer;
import cn.edu.bjtu.ebosnotice.service.MqFactory;
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
    LogService logService;

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
        logService.info("notice");
        return "成功";
    }
    @CrossOrigin
    @GetMapping("/logtest")
    public String logtest2(){
        return logService.findLogByCategory("info");
    }
}
