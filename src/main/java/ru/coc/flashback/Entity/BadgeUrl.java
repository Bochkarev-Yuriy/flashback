package ru.coc.flashback.Entity;

import javax.persistence.*;

/**
 * @author Yuriy Bochkarev
 * @since 24.11.2018.
 */

@Entity
@Table(name = "badge_urls")
public class BadgeUrl {

    @Id
    @TableGenerator(name = "badge_urls_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "badge_urls",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "badge_urls_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "small")
    private String small;

    @Column(name = "large")
    private String large;

    @Column(name = "medium")
    private String medium;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public String toString() {
        return "BadgeUrl{" +
                "id=" + id +
                ", small='" + small + '\'' +
                ", large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                '}';
    }
}
