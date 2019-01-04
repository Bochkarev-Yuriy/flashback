package ru.coc.flashback.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.api.CurrentWarLeagueGroup;
import ru.coc.flashback.service.ClashOfClansAPIClientService;
import ru.coc.flashback.service.LeagueGroupService;

import java.io.IOException;

/**
 * @author Yuriy Bochkarev
 * @since 01.12.2018.
 */

@Service
public class LeagueGroupServiceImpl implements LeagueGroupService {

    @Autowired
    private ClashOfClansAPIClientService clashOfClansAPIClientService;

    public CurrentWarLeagueGroup getLeagueGroupByClanTag(String clanTag) {
        String getRequest = "https://api.clashofclans.com/v1/clans/" + clanTag.replaceAll("#", "%23") + "/currentwar/leaguegroup";
        StringBuilder response = clashOfClansAPIClientService.getResponseByGetRequest(getRequest);
        return getObjectByJson(response, clanTag);
    }

    private CurrentWarLeagueGroup getObjectByJson(StringBuilder response, String clanTag) {
        JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
        ObjectMapper mapper = new ObjectMapper();
        CurrentWarLeagueGroup currentWarLeagueGroup = null;
        try {
            currentWarLeagueGroup = mapper.readValue(jsonObject.toString(), CurrentWarLeagueGroup.class);
        } catch (IOException e) {
        }
        return currentWarLeagueGroup;
    }
}
