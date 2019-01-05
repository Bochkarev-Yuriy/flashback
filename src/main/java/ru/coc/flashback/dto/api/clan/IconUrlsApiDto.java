package ru.coc.flashback.dto.api.clan;

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
public class IconUrlsApiDto {

    private String small;
    private String tiny;
    private String medium;
}
