package cn.edu.bjtu.ebosnotice.service;

public interface LogFind {
    String read(String key, String value);
    String readAll();
}
