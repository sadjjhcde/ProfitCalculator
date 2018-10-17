package com.profit.service.currency;

import org.json.JSONObject;
import com.profit.service.config.ConfigService;
import com.profit.service.http.HTTPService;
import com.profit.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("RateService")
public class RateServiceImpl implements RateService {

    private final HTTPService httpService;
    private final ConfigService configService;
    private final LogService logger;

    private SimpleDateFormat urlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public RateServiceImpl(HTTPService httpService, ConfigService configService, LogService logger) {
        this.httpService = httpService;
        this.configService = configService;
        this.logger = logger;
    }

    public Float getUSDToRUBRateFor(Date date) {
        Float usdRubRate = null;
        if (date != null) {
            String urlDate = urlDateFormat.format(date);
            String getRateUrl = configService.getUsdRubRateUrl();
            getRateUrl = String.format(getRateUrl, urlDate);
            logger.log("RateServiceImpl, Sending request to: " + getRateUrl);
            String jsonResponse = httpService.getHTTPResponse(getRateUrl);
            logger.log("RateServiceImpl, Got response: " + jsonResponse);
            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                JSONObject jsonObj = new JSONObject(jsonResponse);
                usdRubRate = jsonObj.getJSONObject("USD_RUB").getFloat(urlDate);
                logger.log("RateServiceImpl, Got USD_RUB rate: " + usdRubRate);
            }
        }
        return usdRubRate;
    }

}
