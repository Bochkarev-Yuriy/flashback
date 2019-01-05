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
public class TroopApiDto {

    private Long level;
    private Long maxLevel;
    private String name;
    private String village;
}
