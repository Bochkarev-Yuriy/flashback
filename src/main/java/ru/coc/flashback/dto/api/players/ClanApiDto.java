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
public class ClanApiDto {

    private String tag;
    private String name;
    private Long clanLevel;
    private BadgeUrlsApiDto badgeUrls;
}
