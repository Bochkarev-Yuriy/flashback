package ru.coc.flashback.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Entity
@Getter
@Setter
@ToString
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = {"tag"})})
public class Account {

    @Id
    @TableGenerator(name = "account_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "account",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "account_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag", length = 50, unique = true, nullable = false)
    private String tag;

    @Column(name = "registration_name", nullable = false)
    private String registrationName;

    @Column(name = "registration_clan_name", nullable = false)
    private String registrationClanName;

    @Column(name = "registration_clan_tag", length = 50, nullable = false)
    private String registrationClanTag;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @Column (name = "current_name", nullable = false)
    private String currentName;

    @Column(name = "current_clan_name", nullable = false)
    private String currentClanName;

    @Column(name = "current_clan_tag", length = 50, nullable = false)
    private String currentClanTag;
}
