package ru.coc.flashback.dto.api.players;

import lombok.*;
import ru.coc.flashback.dto.api.LeagueApiDto;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PlayersApiDto {

    private String tag;
    private String name;
    private Long townHallLevel;
    private Long townHallWeaponLevel;
    private Long expLevel;
    private Long trophies;
    private Long bestTrophies;
    private Long warStars;
    private Long attackWins;
    private Long defenseWins;
    private Long builderHallLevel;
    private Long versusTrophies;
    private Long bestVersusTrophies;
    private Long versusBattleWins;
    private String role;
    private Long donations;
    private Long donationsReceived;
    private ClanApiDto clan;
    private LeagueApiDto league;
    private LegendStatisticApiDto legendStatistics;
    private List<AchievementApiDto> achievements;
    private Long versusBattleWinCount;
    private List <TroopApiDto> troops;
    private List <HeroApiDto> heroes;
    private List <SpellApiDto> spells;

    public ClanApiDto getClan() {
        if (this.clan == null) {
            ClanApiDto clan = new ClanApiDto();
            clan.setTag("global");
            clan.setName("global");
            this.clan = clan;
        }
        return this.clan;
    }
}
