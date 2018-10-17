package com.profit.service.http;

public interface HTTPService {

    /**
     * Gets json responce for url GET-request
     * @param url url for request
     * @return json string
     */
    String getHTTPResponse(String url);
}
