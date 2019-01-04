package ru.coc.flashback.dto;

/**
 * @author Yuriy Bochkarev
 * @since 22.11.2018.
 */

public class Defender {

    private Long id;
    private String name;
    private String imgUrl;
    private String imgUrlStars;
    private Integer townhallLevel;
    private Integer mapPosition;
    private Integer stars;
    private Integer destructionPercentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrlStars() {
        return imgUrlStars;
    }

    public void setImgUrlStars(String imgUrlStars) {
        this.imgUrlStars = imgUrlStars;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        return "Defender{" +
                "name='" + name + '\'' +
                ", townhallLevel=" + townhallLevel +
                ", mapPosition=" + mapPosition +
                ", stars=" + stars +
                ", destructionPercentage=" + destructionPercentage +
                '}';
    }
}
