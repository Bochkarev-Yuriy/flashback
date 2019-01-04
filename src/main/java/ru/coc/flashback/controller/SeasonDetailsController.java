package ru.coc.flashback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.coc.flashback.dto.SeasonDetailsDTO;
import ru.coc.flashback.service.SeasonDetailService;

/**
 * @author Yuriy Bochkarev
 * @since 23.11.2018.
 */

@RestController
@RequestMapping(value = "/season_detail")
public class SeasonDetailsController {

    @Autowired
    private SeasonDetailService seasonDetailService;

    @GetMapping(value = "/{clanTag}/{seasonName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView getSeasonDetailByClanTag(@PathVariable("clanTag") String clanTag, @PathVariable("seasonName") String seasonName) {
        SeasonDetailsDTO seasonDetailsDTO = seasonDetailService.getSeasonDetailByClanTag(clanTag.replaceAll("%23", "#"), seasonName);
        ModelAndView modelAndView = new ModelAndView("season_detail");
        modelAndView.addObject("seasonDetailsDTO", seasonDetailsDTO);
        return modelAndView;
    }
}
