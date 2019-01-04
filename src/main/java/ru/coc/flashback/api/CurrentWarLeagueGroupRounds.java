package ru.coc.flashback.api;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 14.12.2018.
 */

public class CurrentWarLeagueGroupRounds {

    private List<String> warTags;

    public List<String> getWarTags() {
        return warTags;
    }

    public void setWarTags(List<String> warTags) {
        this.warTags = warTags;
    }

    @Override
    public String toString() {
        return "CurrentWarLeagueGroupRounds{" +
                "warTags=" + warTags +
                '}';
    }
}
