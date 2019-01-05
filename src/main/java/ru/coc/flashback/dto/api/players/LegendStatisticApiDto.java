package ru.coc.flashback.dto.api.players;

import lombok.*;

/**
 * @author Yuriy Bochkarev
 * @since 05.01.2019.
 */

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LegendStatisticApiDto {

    private BestSeasonApiDto bestSeason;
    private BestVersusSeasonApiDto bestVersusSeason;
    private CurrentSeasonApiDto currentSeason;
    private Long legendTrophies;
    private PreviousSeasonApiDto previousSeason;
    private PreviousVersusSeasonApiDto previousVersusSeason;
}
