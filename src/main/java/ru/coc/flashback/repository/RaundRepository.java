package ru.coc.flashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.coc.flashback.entity.Raund;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

public interface RaundRepository extends JpaRepository<Raund, Long> {

    @Query(value = "SELECT *" +
            "        FROM rounds" +
            "        INNER JOIN clan ON rounds.opponent_id = clan.id" +
            "        INNER JOIN season ON rounds.season_id = season.id" +
            "        where clan.tag = :clanTag and season.name = :seasonName", nativeQuery = true)
    List<Raund> getAllRaundsByClanTag(@Param("clanTag") String clanTag, @Param("seasonName") String seasonName);

    @Query(value = "SELECT *" +
            "        FROM rounds" +
            "        INNER JOIN clan ON rounds.clan_id = clan.id" +
            "        INNER JOIN season ON rounds.season_id = season.id" +
            "        where clan.tag = :clanTag and season.name = :seasonName", nativeQuery = true)
    List<Raund> getAllRaundsByClanTagInverce(@Param("clanTag") String clanTag, @Param("seasonName") String seasonName);

    @Query(value = "SELECT * " +
            "FROM rounds " +
            "WHERE war_tag = :warTag", nativeQuery = true)
    Raund getRaundByWarTag(@Param("warTag") String warTag);
}
