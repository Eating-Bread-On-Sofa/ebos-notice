package cn.edu.bjtu.ebosnotice.service;

import cn.edu.bjtu.ebosnotice.dao.NoticeRepo;
import cn.edu.bjtu.ebosnotice.entity.Notice;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Order(1)
public class InitListener implements ApplicationRunner {
    @Autowired
    MqFactory mqFactory;
    @Autowired
    NoticeRepo noticeRepo;
    @Autowired
    LogService logService;
    @Value("${mq}")
    private String name;

    @Override
    public void run(ApplicationArguments arguments) {
        new Thread(() -> {
            MqConsumer mqConsumer = mqFactory.createConsumer("notice");
            while (true) {
                try {
                    String msg = mqConsumer.subscribe();
                    System.out.println("收到：" + msg);
                    JSONObject jsonObject = JSON.parseObject(msg);
                    Notice notice = jsonObject.toJavaObject(Notice.class);
                    notice.setCreated(new Date());
                    noticeRepo.save(notice);
                }catch (Exception e){logService.error(null,e.toString());}
            }
        }).start();
    }
}
