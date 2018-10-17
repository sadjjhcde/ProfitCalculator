package com.profit.service.currency;

import java.util.Date;

public interface RateService {

    /**
     * Gets USD_RUB pair rate from remote com.profit.controller.service.
     * @param date date of rate
     * @return rate
     */
    Float getUSDToRUBRateFor(Date date);
}
