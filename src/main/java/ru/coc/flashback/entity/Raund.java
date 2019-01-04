package ru.coc.flashback.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

@Entity
@Table(name = "rounds")
@JsonIgnoreProperties({"warStartTime", "teamSize"})
public class Raund {

    @Id
    @TableGenerator(name = "raund_entity_gen",
            table = "sequences",
            pkColumnName = "seq_name",
            valueColumnName = "seq_count",
            pkColumnValue = "raund",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "raund_entity_gen")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Season.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Clan.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "clan_id", nullable = false)
    private Clan clan;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Clan.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "opponent_id", nullable = false)
    private Clan opponent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS", timezone = "CET")
    @Column(name = "preparation_start_time")
    private Date preparationStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS", timezone = "CET")
    @Column(name = "war_start_time")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS", timezone = "CET")
    @Column(name = "war_end_time")
    private Date endTime;

    @Column(name = "war_tag", unique = true)
    private String warTag;

    @Column(name = "state")
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWarTag() {
        return warTag;
    }

    public void setWarTag(String warTag) {
        this.warTag = warTag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public Clan getOpponent() {
        return opponent;
    }

    public void setOpponent(Clan opponent) {
        this.opponent = opponent;
    }

    public Date getPreparationStartTime() {
        return preparationStartTime;
    }

    public void setPreparationStartTime(Date preparationStartTime) {
        this.preparationStartTime = preparationStartTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Raund{" +
                "id=" + id +
                ", season=" + season +
                ", clan=" + clan +
                ", opponent=" + opponent +
                ", preparationStartTime=" + preparationStartTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
