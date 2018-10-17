package com.profit.service.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("LogService")
public class LogServiceImpl implements LogService {

    private Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    public void log(String message) {
        logger.info(message);
    }

    public void error(Exception e) {
        logger.error(e.getMessage());
    }
}
