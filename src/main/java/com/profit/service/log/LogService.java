package com.profit.service.log;

public interface LogService {

    void log(String message);

    void error(Exception e);
}
