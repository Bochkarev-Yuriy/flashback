package ru.coc.flashback.service;

import ru.coc.flashback.Entity.SourceDetailWars;

import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 19.12.2018.
 */

public interface SourceDetailWarsService {

    void addSourceDetailWars(List<SourceDetailWars> sourceDetailWars);

    List<SourceDetailWars> getSourceDetailWars();
}
