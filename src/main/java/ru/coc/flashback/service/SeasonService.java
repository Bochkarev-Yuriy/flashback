package ru.coc.flashback.service;

import ru.coc.flashback.Entity.Season;

import java.util.List;
import java.util.Map;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

public interface SeasonService {

    Map<String, List<String>> getAllCurrentWarTagByClanTag();

    Season getSeasonByName(String seasonName);

    Season getSeasonById(Long id);

    Season getSeasonByNameAndClanTag(String seasonName, String clanTag);

    void saveSeason(Season season);
}
