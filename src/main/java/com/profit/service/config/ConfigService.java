package com.profit.service.config;

public interface ConfigService {

    /**
     * @return url for getting exchange rates for USD_RUB pair
     */
    String getUsdRubRateUrl();

    /**
     *
     * @return spread value
     */
    Float getSpread();
}
