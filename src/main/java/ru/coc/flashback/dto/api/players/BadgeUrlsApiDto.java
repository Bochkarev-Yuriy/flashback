package ru.coc.flashback.dto.api.players;

import lombok.*;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Data
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BadgeUrlsApiDto {

    private String small;
    private String large;
    private String medium;
}
