package ru.coc.flashback.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.entity.Season;
import ru.coc.flashback.repository.SeasonRepository;
import ru.coc.flashback.service.ClashOfClansAPIClientService;
import ru.coc.flashback.service.SeasonService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

@Service
public class SeasonServiceImpl implements SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private ClashOfClansAPIClientService clashOfClansAPIClientService;

    @Override
    public Map<String, List<String>> getAllCurrentWarTagByClanTag() {
        String getRequest = "https://api.clashofclans.com/v1/clans/%238P2RCUVR/currentwar/leaguegroup";

        StringBuilder response = clashOfClansAPIClientService.getResponseByGetRequest(getRequest);

        Map<String, List<String>> seasonWarMap = new HashMap<>();
        List<String> wars = new LinkedList<>();
        JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
        JsonArray rounds = jsonObject.getAsJsonArray("rounds");


        for (JsonElement round : rounds) {
            JsonObject roundObj = round.getAsJsonObject();
            JsonArray warTags = roundObj.getAsJsonArray("warTags");
            for (JsonElement warTag : warTags) {
                wars.add(warTag.toString().replaceAll("#", "%23"));
            }
        }
        seasonWarMap.put(jsonObject.get("season").toString(), wars);
        return seasonWarMap;
    }

    @Override
    public Season getSeasonByName(String seasonName) {
        return seasonRepository.getByName(seasonName);
    }

    public Season getSeasonById(Long id) {
        return seasonRepository.getOne(id);
    }

    public Season getSeasonByNameAndClanTag(String seasonName, String clanTag) {
        return seasonRepository.getByNameAndClanTag(seasonName, clanTag);
    }

    public void saveSeason(Season season) {
        if (getSeasonByNameAndClanTag(season.getName(), season.getClanTag()) == null) {
            seasonRepository.save(season);
        }
    }
}
