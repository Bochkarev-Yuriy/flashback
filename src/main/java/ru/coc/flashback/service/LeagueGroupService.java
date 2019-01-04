package ru.coc.flashback.service;

import ru.coc.flashback.api.CurrentWarLeagueGroup;

/**
 * @author Yuriy Bochkarev
 * @since 01.12.2018.
 */

public interface LeagueGroupService {

    CurrentWarLeagueGroup getLeagueGroupByClanTag(String clanTag);
}
