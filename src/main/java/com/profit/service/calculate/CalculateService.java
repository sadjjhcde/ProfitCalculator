package com.profit.service.calculate;

import java.util.Date;

public interface CalculateService {

    /**
     * Calculates profit from buying and selling dollars in rubles using actual exchange rates.
     * @param buyDate date of buy
     * @param sellDate date of sell
     * @param amount amount of purchase
     * @return profit
     */
    Float calculateProfit(Date buyDate, Date sellDate, Integer amount);
}
