package ru.coc.flashback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

@Entity
@Table(name = "season", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "clan_tag"})})
@JsonIgnoreProperties({"id"})
public class Season {

    @Id
    @TableGenerator(name = "season_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "season",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "season_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "clan_tag", length = 50)
    private String clanTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClanTag() {
        return clanTag;
    }

    public void setClanTag(String clanTag) {
        this.clanTag = clanTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(id, season.id) &&
                Objects.equals(name, season.name) &&
                Objects.equals(clanTag, season.clanTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, clanTag);
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clanTag='" + clanTag + '\'' +
                '}';
    }
}
