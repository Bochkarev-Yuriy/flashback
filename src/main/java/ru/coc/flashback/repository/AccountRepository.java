package ru.coc.flashback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.coc.flashback.entity.Account;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT *" +
            "        FROM account" +
            "        WHERE tag = :accountTag",
            nativeQuery = true)
    Account getByTag(@Param("accountTag") String accountTag);
}
