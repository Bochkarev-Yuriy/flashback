package ru.coc.flashback.service;

import org.springframework.data.repository.query.Param;
import ru.coc.flashback.Entity.Raund;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

public interface RoundService {

//    разделить апи от дао
    Raund getRoundByTag(String roundTag);

    List<Raund> getAllRaundsByClanTag(String clanTag, String seasonName);

    List<Raund> getAllRaundsByClanTagInverce(String clanTag, String seasonName);

    void save(Raund raund);

    Raund getRaundByWarTag(String warTag);
}
