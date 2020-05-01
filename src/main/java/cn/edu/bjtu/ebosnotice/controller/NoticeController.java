package cn.edu.bjtu.ebosnotice.controller;

import cn.edu.bjtu.ebosnotice.dao.NoticeRepo;
import cn.edu.bjtu.ebosnotice.entity.Notice;
import com.alibaba.fastjson.JSONArray;
import cn.edu.bjtu.ebosnotice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notice")
@RestController
public class NoticeController {
    @Autowired
    NoticeRepo noticeRepo;
    @Autowired
    LogService logService;

    @CrossOrigin
    @GetMapping("/alert")
    public JSONArray getByType(){
        List<Notice> notices = noticeRepo.findByType("alert");
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(notices);
        return jsonArray;
    }
}
