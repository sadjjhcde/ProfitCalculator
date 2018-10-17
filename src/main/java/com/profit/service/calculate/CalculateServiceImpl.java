package com.profit.service.calculate;

import com.profit.service.config.ConfigService;
import com.profit.service.currency.RateService;
import com.profit.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("calculateService")
public class CalculateServiceImpl implements CalculateService {

    private final RateService rateService;
    private final LogService logger;
    private final ConfigService configService;

    @Autowired
    public CalculateServiceImpl(RateService rateService, LogService logger, ConfigService configService) {
        this.rateService = rateService;
        this.logger = logger;
        this.configService = configService;
    }

    public Float calculateProfit(Date buyDate, Date sellDate, Integer amount) {
        Float buyRate = rateService.getUSDToRUBRateFor(buyDate);
        Float sellRate = rateService.getUSDToRUBRateFor(sellDate);
        Float profit = null;
        if (buyRate != null && sellRate != null) {
            Float rateDifference = sellRate - buyRate;
            Float spreadPercent = configService.getSpread();
            spreadPercent = spreadPercent / 2;
            //get total spread value for buying and selling
            Float spreadValue = (buyRate + sellRate)*amount*spreadPercent;
            profit = rateDifference * amount;
            //subtract spread
            profit = profit - spreadValue;
            logger.log("CalculateServiceImpl, Rate difference: " + rateDifference +
                    ", Spread value: " + spreadValue +
                    " Profit: " + profit);
        }
        return profit;
    }
}
