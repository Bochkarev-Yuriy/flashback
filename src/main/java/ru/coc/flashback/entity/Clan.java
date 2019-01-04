package ru.coc.flashback.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

@Entity
@Table(name = "clan")
public class Clan {

    @Id
    @TableGenerator(name = "clan_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "clan",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "clan_entity_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag")
    private String tag;

    @Column(name = "name")
    private String name;

    @Column(name = "clan_level")
    private Integer clanLevel;

    @Column(name = "attacks")
    private Integer attacks;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "destruction_percentage")
    private Integer destructionPercentage;

    @ManyToOne(targetEntity = BadgeUrl.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "clan_badge_urls",
            joinColumns = {@JoinColumn(name = "clan_id")},
            inverseJoinColumns = {@JoinColumn(name = "badge_urls_id")})
    private BadgeUrl badgeUrls;

    @OneToMany(targetEntity = Member.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "clan_members",
            joinColumns = {@JoinColumn(name = "clan_id")},
            inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private List<Member> members = new ArrayList<>();

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

    public Integer getClanLevel() {
        return clanLevel;
    }

    public void setClanLevel(Integer clanLevel) {
        this.clanLevel = clanLevel;
    }

    public Integer getAttacks() {
        return attacks;
    }

    public void setAttacks(Integer attacks) {
        this.attacks = attacks;
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

    public BadgeUrl getBadgeUrls() {
        return badgeUrls;
    }

    public void setBadgeUrls(BadgeUrl badgeUrls) {
        this.badgeUrls = badgeUrls;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        if (this.members == null) {
            this.members = new ArrayList<>();
        }
        this.members.add(member);
    }

    @Override
    public String toString() {
        return "Clan{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", clanLevel=" + clanLevel +
                ", attacks=" + attacks +
                ", stars=" + stars +
                ", destructionPercentage=" + destructionPercentage +
                ", badgeUrls=" + badgeUrls +
                ", members=" + members +
                '}';
    }
}
