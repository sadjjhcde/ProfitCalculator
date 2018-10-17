package com.profit.service.config;


import com.profit.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service("ConfigService")
public class ConfigServiceImpl implements ConfigService {

    private final LogService logger;
    private final Properties appProps;

    @Autowired
    public ConfigServiceImpl(LogService logger) {
        this.logger = logger;
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "config.properties";
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            this.logger.error(e);
        }
    }

    public String getUsdRubRateUrl() {
        return appProps.getProperty("currencyUrl", null);
    }

    public Float getSpread() {
        String spreadStr = appProps.getProperty("spread", "");
        Float spreadValue = null;
        try {
            spreadValue = Float.valueOf(spreadStr);
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        return spreadValue;
    }
}
