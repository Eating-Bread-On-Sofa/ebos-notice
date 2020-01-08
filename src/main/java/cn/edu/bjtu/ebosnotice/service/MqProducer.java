package cn.edu.bjtu.ebosnotice.service;

public interface MqProducer {
    void publish(String topic, String message);
}
