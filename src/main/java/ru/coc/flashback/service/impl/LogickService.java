package ru.coc.flashback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.coc.flashback.Entity.*;
import ru.coc.flashback.api.CurrentWarLeagueGroup;
import ru.coc.flashback.api.CurrentWarLeagueGroupRounds;
import ru.coc.flashback.service.*;

import java.util.*;

/**
 * @author Yuriy Bochkarev
 * @since 14.12.2018.
 */

@Service
public class LogickService {

    @Autowired
    private SourceClanService sourceClanService;

    @Autowired
    private LeagueGroupService leagueGroupService;

    @Autowired
    private SourceDetailWarsService sourceDetailWarsService;

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private RoundService roundService;

    @Transactional
    public void execute() {
        List<SourceClan> sourceClans = sourceClanService.getAllSourceClan();

        Map<Season, List<String>> seasonAndWars = new HashMap<>();
        for (SourceClan sourceClan : sourceClans) {
            CurrentWarLeagueGroup currentWarLeagueGroup = leagueGroupService.getLeagueGroupByClanTag(sourceClan.getTag());

            if (currentWarLeagueGroup != null) {
                Season seasonClan = new Season();
                seasonClan.setName(currentWarLeagueGroup.getSeason());
                seasonClan.setClanTag(sourceClan.getTag());
                seasonService.saveSeason(seasonClan);


                List<String> warTagList = new ArrayList<>();
                for (CurrentWarLeagueGroupRounds rounds : currentWarLeagueGroup.getRounds()) {
                    for (String warTag : rounds.getWarTags()) {
                        if (!warTag.equals("#0")) {
                            warTagList.add(warTag);
                        }
                    }
                }
                seasonAndWars.put(seasonClan, warTagList);
            }
        }
        synchronizeData(adapter(seasonAndWars));
    }

    @Transactional
    public void synchronizeDataWarTag() {
        synchronizeData(sourceDetailWarsService.getSourceDetailWars());
    }

    private void synchronizeData(List<SourceDetailWars> sourceDetailWarsList) {

        for (SourceDetailWars sourceDetailWar : sourceDetailWarsList) {
            for (WarTag warTag : sourceDetailWar.getWarTags()) {
//                нужно разделить сервисы которые работают с репозиторием и api
                Raund raund = roundService.getRoundByTag(warTag.getWarTag());
                if (raund.getState().equals("warEnded")) {
                    raund.setSeason(seasonService.getSeasonByNameAndClanTag(sourceDetailWar.getSeason().getName(), sourceDetailWar.getSeason().getClanTag()));
                    raund.setWarTag(warTag.getWarTag());
                    roundService.save(raund);
                }
            }
        }
    }

    private List<SourceDetailWars> adapter(Map<Season, List<String>> seasonAndWars) {
        List<SourceDetailWars> sourceDetailWarsList = new ArrayList<>();
        for (Season season : seasonAndWars.keySet()) {
            SourceDetailWars sourceDetailWars = new SourceDetailWars();
            sourceDetailWars.setSeason(season);
            List<WarTag> warTags = new ArrayList<>();
            for (String wt : seasonAndWars.get(season)) {
                WarTag warTag = new WarTag();
                warTag.setWarTag(wt);
                warTags.add(warTag);
            }
            sourceDetailWars.setWarTags(warTags);
            sourceDetailWarsList.add(sourceDetailWars);
        }
        return sourceDetailWarsList;
    }
}
