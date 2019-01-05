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
public class AchievementApiDto {

    private String completionInfo;
    private String info;
    private String name;
    private Long stars;
    private Long target;
    private Long value;
    private String village;
}
