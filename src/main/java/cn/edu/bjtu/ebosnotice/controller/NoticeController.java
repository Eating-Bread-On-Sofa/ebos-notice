package cn.edu.bjtu.ebosnotice.controller;

import cn.edu.bjtu.ebosnotice.dao.NoticeRepo;
import cn.edu.bjtu.ebosnotice.entity.Notice;
import com.alibaba.fastjson.JSONArray;
import cn.edu.bjtu.ebosnotice.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(tags = "通知管理")
@RequestMapping("/api/notice")
@RestController
public class NoticeController {
    @Autowired
    NoticeRepo noticeRepo;
    @Autowired
    LogService logService;

    @ApiImplicitParam(name = "days",value = "查询天数范围,int类型",required = true, paramType = "query")
    @CrossOrigin
    @GetMapping("/alert")
    public JSONArray getByType(@RequestParam int days){
        Date time = daysAgo(days);
        List<Notice> notices = noticeRepo.findByTypeIsAndCreatedAfter("alert",time);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(notices);
        return jsonArray;
    }

    @ApiImplicitParam(name = "days",value = "查询天数范围,int类型",required = true, paramType = "query")
    @CrossOrigin
    @GetMapping("/source/{source}")
    public JSONArray getBySource(@PathVariable String source,@RequestParam int days){
        Date time = daysAgo(days);
        List<Notice> notices = noticeRepo.findBySourceIsAndCreatedAfter(source, time);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(notices);
        return jsonArray;
    }

    private Date daysAgo(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }
}
