package ru.coc.flashback.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuriy Bochkarev
 * @since 19.12.2018.
 */

@Entity
@Table(name = "source_detail_wars", uniqueConstraints = {@UniqueConstraint(columnNames = {"season_id"})})
@JsonIgnoreProperties({"id"})
public class SourceDetailWars {

    @Id
    @TableGenerator(name = "source_detail_wars_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "source_detail_wars",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "source_detail_wars_entity_gen")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Season.class, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(targetEntity = WarTag.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "source_season_war_tag",
            joinColumns = {@JoinColumn(name = "source_detail_war_id")},
            inverseJoinColumns = {@JoinColumn(name = "source_war_tag_id")})
    private List<WarTag> warTags;

//    uniqueConstraints=@UniqueConstraint(columnNames={"Site_ID","users_ID"}

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<WarTag> getWarTags() {
        if (warTags == null) {
            return new ArrayList<>();
        }
        return warTags;
    }

    public void setWarTags(List<WarTag> warTags) {
        this.warTags = warTags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceDetailWars that = (SourceDetailWars) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(season, that.season) &&
                Objects.equals(warTags, that.warTags);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, season, warTags);
    }

    @Override
    public String toString() {
        return "SourceDetailWars{" +
                "id=" + id +
                ", season=" + season +
                ", warTags=" + warTags +
                '}';
    }
}
