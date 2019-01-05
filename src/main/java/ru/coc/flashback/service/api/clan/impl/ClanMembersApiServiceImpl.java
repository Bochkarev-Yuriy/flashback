package ru.coc.flashback.service.api.clan.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.dto.api.clan.ClanMemberApiDto;
import ru.coc.flashback.dto.api.clan.ItemsApiDto;
import ru.coc.flashback.service.ClashOfClansAPIClientService;
import ru.coc.flashback.service.api.clan.ClanMembersApiService;

import java.io.IOException;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Service
public class ClanMembersApiServiceImpl implements ClanMembersApiService {

    private final ClashOfClansAPIClientService clashOfClansAPIClientService;

    @Autowired
    public ClanMembersApiServiceImpl(ClashOfClansAPIClientService clashOfClansAPIClientService) {
        this.clashOfClansAPIClientService = clashOfClansAPIClientService;
    }

    @Override
    public List<ClanMemberApiDto> getClanMembersByTag(String clanTag) {
        String getRequest = "https://api.clashofclans.com/v1/clans/" + clanTag.replaceAll("#", "%23") + "/members";
        StringBuilder response = clashOfClansAPIClientService.getResponseByGetRequest(getRequest);
        return getObjectByJson(response, clanTag);
    }

    private List<ClanMemberApiDto> getObjectByJson(StringBuilder response, String clanTag) {
        JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();
        ObjectMapper mapper = new ObjectMapper();
        ItemsApiDto itemsApiDto = null;
        try {
            itemsApiDto = mapper.readValue(jsonObject.toString(), ItemsApiDto.class);
        } catch (IOException e) {
        }
        return itemsApiDto != null ? itemsApiDto.getItems() : null;
    }
}
