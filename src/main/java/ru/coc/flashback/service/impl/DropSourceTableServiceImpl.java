package ru.coc.flashback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.repository.SourceClanRepository;
import ru.coc.flashback.repository.SourceDetailWarsRepository;
import ru.coc.flashback.service.DropSourceTableService;

/**
 * @author Yuriy Bochkarev
 * @since 26.12.2018.
 */

@Service
public class DropSourceTableServiceImpl implements DropSourceTableService {

    @Autowired
    private SourceDetailWarsRepository sourceDetailWarsRepository;

    @Autowired
    private SourceClanRepository sourceClanRepository;

    @Override
    public void execute() {
        sourceDetailWarsRepository.deleteAll();
        sourceClanRepository.deleteAll();
    }
}
