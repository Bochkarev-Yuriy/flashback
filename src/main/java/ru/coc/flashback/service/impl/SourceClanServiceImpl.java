package ru.coc.flashback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.Entity.SourceClan;
import ru.coc.flashback.repository.SourceClanRepository;
import ru.coc.flashback.service.SourceClanService;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 14.12.2018.
 */

@Service
public class SourceClanServiceImpl implements SourceClanService {

    @Autowired
    private SourceClanRepository sourceClanRepository;

    @Override
    public List<SourceClan> getAllSourceClan() {
        return sourceClanRepository.findAll();
    }

    @Override
    public void addSourceClan(List<SourceClan> sourceClans) {
        for (SourceClan sourceClan : sourceClans) {
            if (sourceClanRepository.getByTag(sourceClan.getTag()) == null) {
                sourceClanRepository.save(sourceClan);
            }
        }
    }
}
