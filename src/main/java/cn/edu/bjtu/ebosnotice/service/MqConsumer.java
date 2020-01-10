package cn.edu.bjtu.ebosnotice.service;

import org.springframework.stereotype.Service;

@Service
public interface MqConsumer {
    String subscribe();
}
