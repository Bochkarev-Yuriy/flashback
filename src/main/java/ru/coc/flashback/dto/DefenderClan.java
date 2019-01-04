package ru.coc.flashback.dto;

import java.util.Date;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

public class DefenderClan {

    private Date startTime;
    private String attackerName;
    private String defenderName;
    private String attackerBadgeUrl;
    private String defenderBadgeUrl;
    private String defenderClanTag;
    private Integer attackerClanLevel;
    private Integer defenderClanLevel;
    private Integer attackerStars;
    private Integer defenderStars;
    private List<Attacker> attackers;

    public String getAttackerBadgeUrl() {
        return attackerBadgeUrl;
    }

    public void setAttackerBadgeUrl(String attackerBadgeUrl) {
        this.attackerBadgeUrl = attackerBadgeUrl;
    }

    public String getDefenderBadgeUrl() {
        return defenderBadgeUrl;
    }

    public void setDefenderBadgeUrl(String defenderBadgeUrl) {
        this.defenderBadgeUrl = defenderBadgeUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getAttackerName() {
        return attackerName;
    }

    public void setAttackerName(String attackerName) {
        this.attackerName = attackerName;
    }

    public String getDefenderName() {
        return defenderName;
    }

    public void setDefenderName(String defenderName) {
        this.defenderName = defenderName;
    }

    public Integer getAttackerClanLevel() {
        return attackerClanLevel;
    }

    public void setAttackerClanLevel(Integer attackerClanLevel) {
        this.attackerClanLevel = attackerClanLevel;
    }

    public Integer getDefenderClanLevel() {
        return defenderClanLevel;
    }

    public void setDefenderClanLevel(Integer defenderClanLevel) {
        this.defenderClanLevel = defenderClanLevel;
    }

    public Integer getAttackerStars() {
        return attackerStars;
    }

    public void setAttackerStars(Integer attackerStars) {
        this.attackerStars = attackerStars;
    }

    public Integer getDefenderStars() {
        return defenderStars;
    }

    public void setDefenderStars(Integer defenderStars) {
        this.defenderStars = defenderStars;
    }

    public List<Attacker> getAttackers() {
        return attackers;
    }

    public void setAttackers(List<Attacker> attackers) {
        this.attackers = attackers;
    }

    public String getDefenderClanTag() {
        return defenderClanTag;
    }

    public void setDefenderClanTag(String defenderClanTag) {
        this.defenderClanTag = defenderClanTag;
    }

    @Override
    public String toString() {
        return "DefenderClan{" +
                "startTime=" + startTime +
                ", attackerName='" + attackerName + '\'' +
                ", defenderName='" + defenderName + '\'' +
                ", attackerBadgeUrl='" + attackerBadgeUrl + '\'' +
                ", defenderBadgeUrl='" + defenderBadgeUrl + '\'' +
                ", defenderClanTag='" + defenderClanTag + '\'' +
                ", attackerClanLevel=" + attackerClanLevel +
                ", defenderClanLevel=" + defenderClanLevel +
                ", attackerStars=" + attackerStars +
                ", defenderStars=" + defenderStars +
                ", attackers=" + attackers +
                '}';
    }
}
