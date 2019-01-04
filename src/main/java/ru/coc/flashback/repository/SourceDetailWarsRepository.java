package ru.coc.flashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.coc.flashback.entity.SourceDetailWars;

/**
 * @author Yuriy Bochkarev
 * @since 19.12.2018.
 */

public interface SourceDetailWarsRepository extends JpaRepository<SourceDetailWars, Long> {

    @Query(value = "SELECT *" +
            "        FROM source_detail_wars" +
            "        WHERE season_id = :season_id",
            nativeQuery = true)
    SourceDetailWars getSourceDetailWarsBySeasonId(@Param("season_id") Long seasonId);
}
