package ru.coc.flashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.coc.flashback.Entity.Clan;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

public interface ClanRepository extends JpaRepository<Clan, Long> {
}
