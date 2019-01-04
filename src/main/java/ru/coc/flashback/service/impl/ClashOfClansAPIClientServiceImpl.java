package ru.coc.flashback.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.coc.flashback.service.ClashOfClansAPIClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Yuriy Bochkarev
 * @since 01.12.2018.
 */

@Service
public class ClashOfClansAPIClientServiceImpl implements ClashOfClansAPIClientService {

    @Value("${developer.api.clashofclans.com.token}")
    private String clashOfClansToken;

    public StringBuilder getResponseByGetRequest (String url) {

        URL obj;
        StringBuilder response = new StringBuilder();
        try {
            obj = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("authorization", "Bearer " + clashOfClansToken);
            BufferedReader bufferedReader;
            if (connection.getResponseCode() == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
