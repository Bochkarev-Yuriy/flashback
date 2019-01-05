package ru.coc.flashback.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.coc.flashback.entity.SourceClan;
import ru.coc.flashback.entity.SourceDetailWars;
import ru.coc.flashback.service.DropSourceTableService;
import ru.coc.flashback.service.SourceClanService;
import ru.coc.flashback.service.SourceDetailWarsService;
import ru.coc.flashback.service.impl.LogickService;
import ru.coc.flashback.service.job.RegistrationAccountJobService;
import ru.coc.flashback.service.job.UpdateAccountJobService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

@RestController
@RequestMapping(value = "/admin")
@Api(value = "/api", tags = {"Модуль"}, description = "Admin method")
public class ApiController {

    @Autowired
    private SourceDetailWarsService sourceDetailWarsService;

    @Autowired
    private SourceClanService sourceClanService;

    @Autowired
    private LogickService logickService;

    @Autowired
    private DropSourceTableService dropSourceTableService;

    @Autowired
    private RegistrationAccountJobService registrationAccountJobService;

    @Autowired
    private UpdateAccountJobService updateAccountJobService;

    @GetMapping(value = "/synchronize_data_end_war", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Синхронезировать данные по завершенной войне", nickname = "/synchronize_data_end_war", httpMethod = "GET")
    public void synchronizeDataEndWar() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController run synchronize clans war");
        logickService.execute();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController end synchronize clans war");
    }

    @PostMapping(value = "/add_source_clan", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Добовляе клан для мониторинга его ЛКВ", nickname = "/add_source_clan", httpMethod = "POST")
    public void addSourceClan(@RequestBody List<SourceClan> sourceClans) {
        sourceClanService.addSourceClan(sourceClans);
    }

    @PostMapping(value = "/add_war_tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Добавить тег войны в ручную", nickname = "/add_war_tags", httpMethod = "POST")
    public void addWarTag(@RequestBody List<SourceDetailWars> sourceDetailWars) {
        sourceDetailWarsService.addSourceDetailWars(sourceDetailWars);
    }

    @GetMapping(value = "/get_war_tags", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Получить все теги войны", nickname = "/get_war_tags", httpMethod = "GET")
    public List<SourceDetailWars> getWarTag() {
        return sourceDetailWarsService.getSourceDetailWars();
    }

    @GetMapping(value = "/synchronize_data_war_tag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Синхронезировать данные по тегам войны", nickname = "/synchronize_data_war_tag", httpMethod = "GET")
    public void synchronizeDataWarTag() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController run synchronize war tag");
        logickService.synchronizeDataWarTag();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController end synchronize war tag");
    }

    @GetMapping(value = "/drop_source_tables", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Удалить исходные таблицы", nickname = "/synchronize_data_war_tag", httpMethod = "GET")
    public void dropSourceTables() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController run drop source tables");
        dropSourceTableService.execute();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController end drop source tables");
    }

    @GetMapping(value = "/registration_account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Регестрировать аккаунты которые находяться в кланах", nickname = "/registration_account", httpMethod = "GET")
    public void registrationAccount() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController run registration account");
        registrationAccountJobService.execute();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController end registration account");
    }

    @GetMapping(value = "/update_account", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Обновить данные по аккаунтам", nickname = "/update_account", httpMethod = "GET")
    public void updateAccount() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController run update account");
        updateAccountJobService.execute();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + " RestController end update account");
    }
}
