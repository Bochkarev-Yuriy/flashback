package ru.coc.flashback.service.api.clan.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.dto.api.players.PlayersApiDto;
import ru.coc.flashback.service.ClashOfClansAPIClientService;
import ru.coc.flashback.service.api.clan.PlayersApiService;

import java.io.IOException;

/**
 * @author Yuriy Bochkarev
 * @since 05.01.2019.
 */

@Service
public class PlayersApiServiceImpl implements PlayersApiService {

    private final ClashOfClansAPIClientService clashOfClansAPIClientService;

    @Autowired
    public PlayersApiServiceImpl(ClashOfClansAPIClientService clashOfClansAPIClientService) {
        this.clashOfClansAPIClientService = clashOfClansAPIClientService;
    }

    @Override
    public PlayersApiDto getPlayersApiDtoByTag(String playerTag) {
        String getRequest = "https://api.clashofclans.com/v1/players/" + playerTag.replaceAll("#", "%23");
        StringBuilder response = clashOfClansAPIClientService.getResponseByGetRequest(getRequest);
        return getObjectByJson(response);
    }

    private PlayersApiDto getObjectByJson(StringBuilder response) {
        JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
        ObjectMapper mapper = new ObjectMapper();
        PlayersApiDto playersApiDto = null;
        try {
            playersApiDto = mapper.readValue(jsonObject.toString(), PlayersApiDto.class);
        } catch (IOException e) {
        }
        return playersApiDto;
    }
}
