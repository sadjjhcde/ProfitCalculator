package com.profit.service.http;

import com.profit.service.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("HTTPService")
public class HTTPServiceImpl implements HTTPService {

    private final LogService logger;

    @Autowired
    public HTTPServiceImpl(LogService logger) {
        this.logger = logger;
    }

    public String getHTTPResponse(String url) {
        String result = null;
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            logger.log("HTTPServiceImpl, Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            result = response.toString();
        } catch (IOException e ) {
            logger.error(e);
        }
        return result;
    }
}
