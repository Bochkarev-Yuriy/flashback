package ru.coc.flashback.dto;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

public class Attacker {

    private String name;
    private String tag;
    private String imgUrl;
    private Integer townhallLevel;
    private Integer mapPosition;
    private List<Defender> defenders;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public List<Defender> getDefenders() {
        return defenders;
    }

    public void setDefenders(List<Defender> defenders) {
        this.defenders = defenders;
    }

    @Override
    public String toString() {
        return "Attacker{" +
                "name='" + name + '\'' +
                ", townhallLevel=" + townhallLevel +
                ", mapPosition=" + mapPosition +
                ", defenders=" + defenders +
                '}';
    }
}
