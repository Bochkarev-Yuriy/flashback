package ru.coc.flashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.coc.flashback.Entity.SourceClan;

/**
 * @author Yuriy Bochkarev
 * @since 14.12.2018.
 */

public interface SourceClanRepository extends JpaRepository<SourceClan, Long> {

    SourceClan getByName(String name);

    @Query(value = "SELECT *" +
            "        FROM source_clan" +
            "        WHERE tag = :clanTag",
            nativeQuery = true)
    SourceClan getByTag(@Param("clanTag") String clanTag);
}