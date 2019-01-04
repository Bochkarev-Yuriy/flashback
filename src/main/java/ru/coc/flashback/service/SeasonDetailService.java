package ru.coc.flashback.service;

import ru.coc.flashback.dto.SeasonDetailsDTO;

/**
 * @author Yuriy Bochkarev
 * @since 23.11.2018.
 */

public interface SeasonDetailService {

    SeasonDetailsDTO getSeasonDetailByClanTag(String clanTag, String seasonName);
}
