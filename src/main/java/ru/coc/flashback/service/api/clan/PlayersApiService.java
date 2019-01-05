package ru.coc.flashback.service.api.clan;

import ru.coc.flashback.dto.api.players.PlayersApiDto;

/**
 * @author Yuriy Bochkarev
 * @since 05.01.2019.
 */

public interface PlayersApiService {

    PlayersApiDto getPlayersApiDtoByTag(String playerTag);
}
