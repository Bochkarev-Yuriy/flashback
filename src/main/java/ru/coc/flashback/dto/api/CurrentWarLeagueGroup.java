package ru.coc.flashback.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 14.12.2018.
 */

@JsonIgnoreProperties({"clans"})
public class CurrentWarLeagueGroup {

    private String state;
    private String season;
    private ArrayList<Object> clans;
    private List<CurrentWarLeagueGroupRounds> rounds;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public ArrayList<Object> getClans() {
        return clans;
    }

    public void setClans(ArrayList<Object> clans) {
        this.clans = clans;
    }

    public List<CurrentWarLeagueGroupRounds> getRounds() {
        return rounds;
    }

    public void setRounds(List<CurrentWarLeagueGroupRounds> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "CurrentWarLeagueGroup{" +
                "state='" + state + '\'' +
                ", season='" + season + '\'' +
                ", clans=" + clans +
                ", rounds=" + rounds +
                '}';
    }
}
