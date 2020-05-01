package cn.edu.bjtu.ebosnotice.dao;

import cn.edu.bjtu.ebosnotice.entity.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NoticeRepo extends MongoRepository<Notice, String> {
    List<Notice> findByType(String type);
    List<Notice> findBySource(String source);
    List<Notice> findByCreatedBetween(Date start, Date end);
    List<Notice> findByTypeAndSource(String type, String source);
    List<Notice> findByTypeIsAndCreatedBetween(String type, Date start, Date end);
    List<Notice> findBySourceIsAndCreatedBetween(String source, Date start, Date end);
    List<Notice> findByTypeIsAndSourceIsAndCreatedBetween(String type, String source, Date start, Date end);
}
