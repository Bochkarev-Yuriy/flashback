package ru.coc.flashback.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */


@Entity
@JsonIgnoreProperties({"order"})
@Table(name = "attack")
public class Attack {

    @Id
    @TableGenerator(name = "attack_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "attack",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "attack_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "attacker_tag")
    private String attackerTag;

    @Column(name = "defender_tag")
    private String defenderTag;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "destruction_percentage")
    private Integer destructionPercentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttackerTag() {
        return attackerTag;
    }

    public void setAttackerTag(String attackerTag) {
        this.attackerTag = attackerTag;
    }

    public String getDefenderTag() {
        return defenderTag;
    }

    public void setDefenderTag(String defenderTag) {
        this.defenderTag = defenderTag;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getDestructionPercentage() {
        return destructionPercentage;
    }

    public void setDestructionPercentage(Integer destructionPercentage) {
        this.destructionPercentage = destructionPercentage;
    }

    @Override
    public String toString() {
        return "Attack{" +
                "id=" + id +
                ", attackerTag='" + attackerTag + '\'' +
                ", defenderTag='" + defenderTag + '\'' +
                ", stars=" + stars +
                ", destructionPercentage=" + destructionPercentage +
                '}';
    }
}
