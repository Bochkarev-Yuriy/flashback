package ru.coc.flashback.service.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.dto.api.clan.ClanMemberApiDto;
import ru.coc.flashback.entity.Account;
import ru.coc.flashback.entity.SourceClan;
import ru.coc.flashback.repository.AccountRepository;
import ru.coc.flashback.service.SourceClanService;
import ru.coc.flashback.service.api.clan.ClanMembersApiService;
import ru.coc.flashback.service.job.RegistrationAccountJobService;

import java.util.Date;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Service
public class RegistrationAccountJobServiceImpl implements RegistrationAccountJobService {

    private final SourceClanService sourceClanService;
    private final ClanMembersApiService clanMembersApiService;
    private final AccountRepository accountRepository;

    @Autowired
    public RegistrationAccountJobServiceImpl(SourceClanService sourceClanService, ClanMembersApiService clanMembersApiService, AccountRepository accountRepository) {
        this.sourceClanService = sourceClanService;
        this.clanMembersApiService = clanMembersApiService;
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute() {

        List<SourceClan> sourceClanList = sourceClanService.getAllSourceClan();

        for (SourceClan sourceClan : sourceClanList) {
            List<ClanMemberApiDto> clanMemberApiDtoList = clanMembersApiService.getClanMembersByTag(sourceClan.getTag());
            for (ClanMemberApiDto clanMemberApiDto : clanMemberApiDtoList) {
                Account account = accountRepository.getByTag(clanMemberApiDto.getTag());
                if (account == null) {
                    account = adapter(clanMemberApiDto, sourceClan);
                    accountRepository.save(account);
                }
            }
        }

    }

    private Account adapter(ClanMemberApiDto clanMemberApiDto, SourceClan sourceClan) {
        Account account = new Account();
        account.setTag(clanMemberApiDto.getTag());
        account.setRegistrationName(clanMemberApiDto.getName());
        account.setRegistrationClanName(sourceClan.getName());
        account.setRegistrationClanTag(sourceClan.getTag());
        account.setRegistrationDate(new Date());
        account.setCurrentName(clanMemberApiDto.getName());
        account.setCurrentClanName(sourceClan.getName());
        account.setCurrentClanTag(sourceClan.getTag());
        return account;
    }

}
