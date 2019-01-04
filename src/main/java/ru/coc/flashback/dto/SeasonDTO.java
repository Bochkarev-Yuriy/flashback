package ru.coc.flashback.dto;

import java.util.Map;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

public class SeasonDTO {

    private String name;
    private String season;
    private Map<String, DefenderClan> seasonWars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, DefenderClan> getSeasonWars() {
        return seasonWars;
    }

    public void setSeasonWars(Map<String, DefenderClan> seasonWars) {
        this.seasonWars = seasonWars;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
