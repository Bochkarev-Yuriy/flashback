package ru.coc.flashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.coc.flashback.entity.Season;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

public interface SeasonRepository extends JpaRepository<Season, Long> {

    Season getByName(String seasonName);

    @Query(value = "SELECT *" +
            "        FROM season" +
            "        where name = :seasonName and clan_tag = :clanTag",
            nativeQuery = true)
    Season getByNameAndClanTag(@Param("seasonName") String seasonName, @Param("clanTag") String clanTag);
}
