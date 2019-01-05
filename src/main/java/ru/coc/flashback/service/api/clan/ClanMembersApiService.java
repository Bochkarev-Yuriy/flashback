package ru.coc.flashback.service.api.clan;

import ru.coc.flashback.dto.api.clan.ClanMemberApiDto;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

public interface ClanMembersApiService {

    List<ClanMemberApiDto> getClanMembersByTag(String clanTag);
}
