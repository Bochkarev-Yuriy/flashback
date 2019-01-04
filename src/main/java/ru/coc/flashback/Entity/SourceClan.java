package ru.coc.flashback.Entity;

import javax.persistence.*;

/**
 * @author Yuriy Bochkarev
 * @since 14.12.2018.
 */

@Entity
@Table(name = "source_clan")
public class SourceClan {

    @Id
    @TableGenerator(name = "source_clan_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "source_clan",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "source_clan_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag", length = 50, unique = true)
    private String tag;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SourceClan{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
