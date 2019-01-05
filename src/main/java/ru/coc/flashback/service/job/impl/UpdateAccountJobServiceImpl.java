package ru.coc.flashback.service.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.dto.api.players.PlayersApiDto;
import ru.coc.flashback.entity.Account;
import ru.coc.flashback.repository.AccountRepository;
import ru.coc.flashback.service.api.clan.PlayersApiService;
import ru.coc.flashback.service.job.UpdateAccountJobService;

import java.util.List;
import java.util.Objects;

/**
 * @author Yuriy Bochkarev
 * @since 04.01.2019.
 */

@Service
public class UpdateAccountJobServiceImpl implements UpdateAccountJobService {

    private final AccountRepository accountRepository;
    private final PlayersApiService playersApiService;

    @Autowired
    public UpdateAccountJobServiceImpl(AccountRepository accountRepository, PlayersApiService playersApiService) {
        this.accountRepository = accountRepository;
        this.playersApiService = playersApiService;
    }

    public void execute() {
        List<Account> accountList = accountRepository.findAll();
        for (Account account : accountList) {
            PlayersApiDto playersApiDto = playersApiService.getPlayersApiDtoByTag(account.getTag());
            if (!equals(account, playersApiDto)) {
                account.setCurrentName(playersApiDto.getName());
                account.setCurrentClanName(playersApiDto.getClan().getName());
                account.setCurrentClanTag(playersApiDto.getClan().getTag());
                accountRepository.save(account);
            }
        }
    }

    private boolean equals(Account account, PlayersApiDto playersApiDto) {
        return Objects.equals(account.getCurrentName(), playersApiDto.getName()) &&
                Objects.equals(account.getCurrentClanName(), playersApiDto.getClan().getName()) &&
                Objects.equals(account.getCurrentClanTag(), playersApiDto.getClan().getTag());
    }
}
