package ru.coc.flashback.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

@Entity
@JsonIgnoreProperties({"bestOpponentAttack"})
@Table(name = "members")
public class Member {

    @Id
    @TableGenerator(name = "members_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "members",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "members_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag")
    private String tag;

    @Column(name = "name")
    private String name;

    @Column(name = "townhall_level")
    private Integer townhallLevel;

    @Column(name = "map_position")
    private Integer mapPosition;

    @Column(name = "opponent_attacks")
    private Integer opponentAttacks;

    @OneToMany(targetEntity = Attack.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "member_attacks",
            joinColumns = {@JoinColumn(name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "attack_id")})
    private List<Attack> attacks;

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

    public Integer getTownhallLevel() {
        return townhallLevel;
    }

    public void setTownhallLevel(Integer townhallLevel) {
        this.townhallLevel = townhallLevel;
    }

    public Integer getMapPosition() {
        return mapPosition;
    }

    public void setMapPosition(Integer mapPosition) {
        this.mapPosition = mapPosition;
    }

    public Integer getOpponentAttacks() {
        return opponentAttacks;
    }

    public void setOpponentAttacks(Integer opponentAttacks) {
        this.opponentAttacks = opponentAttacks;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public void addAttack(Attack attack) {
        if (this.attacks == null) {
            this.attacks = new ArrayList<>();
        }
        this.attacks.add(attack);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", townhallLevel=" + townhallLevel +
                ", mapPosition=" + mapPosition +
                ", opponentAttacks=" + opponentAttacks +
                ", attacks=" + attacks +
                '}';
    }
}
