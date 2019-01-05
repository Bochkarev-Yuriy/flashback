package ru.coc.flashback.dto.api;

import lombok.*;
import ru.coc.flashback.dto.api.clan.IconUrlsApiDto;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LeagueApiDto {

    private Long id;
    private String name;
    private IconUrlsApiDto iconUrls;
}
