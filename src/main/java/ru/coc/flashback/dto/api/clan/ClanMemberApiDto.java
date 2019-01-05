package ru.coc.flashback.dto.api.clan;

import lombok.*;
import ru.coc.flashback.dto.api.LeagueApiDto;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ClanMemberApiDto {

    private String tag;
    private String name;
    private String role;
    private Long expLevel;
    private LeagueApiDto league;
    private Long trophies;
    private Long versusTrophies;
    private Long clanRank;
    private Long previousClanRank;
    private Long donations;
    private Long donationsReceived;
}
