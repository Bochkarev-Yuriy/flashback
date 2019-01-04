package ru.coc.flashback.service;

import ru.coc.flashback.Entity.SourceClan;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 14.12.2018.
 */

public interface SourceClanService {

    List<SourceClan> getAllSourceClan();

    void addSourceClan(List<SourceClan> sourceClans);
}
