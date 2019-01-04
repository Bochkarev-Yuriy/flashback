package ru.coc.flashback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coc.flashback.entity.Raund;
import ru.coc.flashback.entity.Season;
import ru.coc.flashback.entity.SourceDetailWars;
import ru.coc.flashback.entity.WarTag;
import ru.coc.flashback.repository.RaundRepository;
import ru.coc.flashback.repository.SeasonRepository;
import ru.coc.flashback.repository.SourceDetailWarsRepository;
import ru.coc.flashback.repository.WarTagRepository;
import ru.coc.flashback.service.SourceDetailWarsService;

import java.util.*;

/**
 * @author Yuriy Bochkarev
 * @since 19.12.2018.
 */

@Service
public class SourceDetailWarsServiceImpl implements SourceDetailWarsService {

    @Autowired
    private SourceDetailWarsRepository sourceDetailWarsRepository;

    @Autowired
    private WarTagRepository warTagRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private RaundRepository raundRepository;

    @Transactional
    public void addSourceDetailWars(List<SourceDetailWars> sourceDetailWars) {

        List<SourceDetailWars> sourceDetailWarsList = new ArrayList<>();
        for(SourceDetailWars sw : sourceDetailWars) {
            SourceDetailWars sourceDetailWarsEntity = new SourceDetailWars();
            Season seasonSource = sw.getSeason();
            Season seasonEntity = seasonRepository.getByNameAndClanTag(seasonSource.getName(), seasonSource.getClanTag());
            if (seasonEntity != null) {
//                getSourceDetailWars by season
                SourceDetailWars sdw = sourceDetailWarsRepository.getSourceDetailWarsBySeasonId(seasonEntity.getId());

                if (sdw != null) {
                    sourceDetailWarsEntity = sdw;
                }

                List<WarTag> warTagList = new ArrayList<>();
                for (WarTag wt : sw.getWarTags()) {

                    WarTag warTagEntity = warTagRepository.getWarTag(wt.getWarTag());
                    if (warTagEntity == null) {
                        warTagList.add(wt);
                    }

                }
                sourceDetailWarsEntity.setSeason(seasonEntity);
                sourceDetailWarsEntity.getWarTags().addAll(warTagList);
                sourceDetailWarsList.add(sourceDetailWarsEntity);
            } else {
                sourceDetailWarsEntity.setSeason(seasonSource);
                sourceDetailWarsEntity.setWarTags(sw.getWarTags());
                sourceDetailWarsList.add(sourceDetailWarsEntity);
            }
        }
        sourceDetailWarsRepository.saveAll(sourceDetailWarsList);
    }

    public List<SourceDetailWars> getSourceDetailWars() {

        Set<SourceDetailWars> sourceDetailWarsList = new HashSet<>();
        Map<Season, List<String>> seasonWarTags = new HashMap<>();
        List<Raund> raunds = raundRepository.findAll();

        for (Raund raund : raunds) {
            if (!seasonWarTags.keySet().contains(raund.getSeason())) {
                seasonWarTags.put(raund.getSeason(), new ArrayList<>());
            }
            seasonWarTags.get(raund.getSeason()).add(raund.getWarTag());
        }

        for (Season season : seasonWarTags.keySet()) {
            List<WarTag> warTags = new ArrayList<>();
            for (String warTagString : seasonWarTags.get(season)) {
                WarTag warTag = new WarTag();
                warTag.setWarTag(warTagString);
                warTags.add(warTag);
            }
            SourceDetailWars sourceDetailWars = new SourceDetailWars();
            sourceDetailWars.setSeason(season);
            sourceDetailWars.setWarTags(warTags);
            sourceDetailWarsList.add(sourceDetailWars);
        }
        sourceDetailWarsList.addAll(sourceDetailWarsRepository.findAll());
        return new ArrayList<>(sourceDetailWarsList);
    }
}
