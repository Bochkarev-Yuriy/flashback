package ru.coc.flashback.dto;

import java.util.List;
import java.util.Map;

/**
 * @author Yuriy Bochkarev
 * @since 23.11.2018.
 */

public class SeasonDetailsDTO {

    private String season;
    private List<DefenderClan> defenderClans;
    private Map<String, Map<String, List<Attacker>>> usersAttack;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public List<DefenderClan> getDefenderClans() {
        return defenderClans;
    }

    public void setDefenderClans(List<DefenderClan> defenderClans) {
        this.defenderClans = defenderClans;
    }

    public Map<String, Map<String, List<Attacker>>> getUsersAttack() {
        return usersAttack;
    }

    public void setUsersAttack(Map<String, Map<String, List<Attacker>>> usersAttack) {
        this.usersAttack = usersAttack;
    }

    @Override
    public String toString() {
        return "SeasonDetailsDTO{" +
                "season='" + season + '\'' +
                ", defenderClans=" + defenderClans +
                ", usersAttack=" + usersAttack +
                '}';
    }
}
