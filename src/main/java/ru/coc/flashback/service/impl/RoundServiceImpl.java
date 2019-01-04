package ru.coc.flashback.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.entity.Raund;
import ru.coc.flashback.repository.RaundRepository;
import ru.coc.flashback.service.ClashOfClansAPIClientService;
import ru.coc.flashback.service.RoundService;

import java.io.IOException;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

@Service
public class RoundServiceImpl implements RoundService {

    @Autowired
    private RaundRepository raundRepository;

    @Autowired
    private ClashOfClansAPIClientService clashOfClansAPIClientService;

    public Raund getRoundByTag(String roundTag) {

        //    https://api.clashofclans.com/v1/clanwarleagues/wars/%2328R0R9JR8

        String getRequest = "https://api.clashofclans.com/v1/clanwarleagues/wars/" + roundTag.replaceAll("#", "%23");
        StringBuilder response = clashOfClansAPIClientService.getResponseByGetRequest(getRequest);

        JsonObject jsonObject = new JsonParser().parse(response.toString()).getAsJsonObject();

        ObjectMapper mapper = new ObjectMapper();
        Raund raund = null;
        try {
            raund = mapper.readValue(jsonObject.toString(), Raund.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raund;

    }

    @Override
    public List<Raund> getAllRaundsByClanTag(String clanTag, String seasonName) {
        return raundRepository.getAllRaundsByClanTag(clanTag, seasonName);
    }

    @Override
    public List<Raund> getAllRaundsByClanTagInverce(String clanTag, String seasonName) {
        return raundRepository.getAllRaundsByClanTagInverce(clanTag, seasonName);
    }

    @Override
    public void save(Raund raund) {
        if (getRaundByWarTag(raund.getWarTag()) == null) {
            raundRepository.save(raund);
        }
    }

    @Override
    public Raund getRaundByWarTag(String warTag) {
        return raundRepository.getRaundByWarTag(warTag);
    }
}
