package ru.coc.flashback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Yuriy Bochkarev
 * @since 19.12.2018.
 */

@Entity
@Table(name = "source_war_tag", uniqueConstraints = {@UniqueConstraint(columnNames = {"war_tag"})})
@JsonIgnoreProperties({"id"})
public class WarTag {

    @Id
    @TableGenerator(name = "source_war_tag_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "source_war_tag",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "source_war_tag_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "war_tag", length = 50)
    private String warTag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarTag() {
        return warTag;
    }

    public void setWarTag(String warTag) {
        this.warTag = warTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarTag warTag1 = (WarTag) o;
        return Objects.equals(id, warTag1.id) &&
                Objects.equals(warTag, warTag1.warTag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, warTag);
    }

    @Override
    public String toString() {
        return "WarTag{" +
                "id=" + id +
                ", warTag='" + warTag + '\'' +
                '}';
    }
}
