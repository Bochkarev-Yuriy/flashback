package ru.coc.flashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.coc.flashback.Entity.WarTag;

/**
 * @author Yuriy Bochkarev
 * @since 23.12.2018.
 */

public interface WarTagRepository extends JpaRepository<WarTag, Long> {

    @Query(value = "SELECT *" +
            "        FROM source_war_tag" +
            "        WHERE war_tag = :war_tag",
            nativeQuery = true)
    WarTag getWarTag(@Param("war_tag") String war_tag);
}