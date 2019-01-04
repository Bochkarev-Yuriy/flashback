package ru.coc.flashback.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.coc.flashback.entity.*;
import ru.coc.flashback.dto.*;
import ru.coc.flashback.service.RoundService;
import ru.coc.flashback.service.SeasonDetailService;
import ru.coc.flashback.service.SeasonService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yuriy Bochkarev
 * @since 18.11.2018.
 */

@Service
public class SeasonDetailServiceImpl implements SeasonDetailService {

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private RoundService roundService;

    @Override
    public SeasonDetailsDTO getSeasonDetailByClanTag(String clanTag, String seasonName) {
//      "#8P2RCUVR", "2018-11"
        SeasonDTO raunds = getAttacker(clanTag, seasonName);

        Set<String> defenderClans = raunds.getSeasonWars().keySet();

        Set<String> userAttackersTag = new HashSet<>();


        List<DefenderClan> defenderClanList = new ArrayList<>();
        for (String clanName : defenderClans) {
            defenderClanList.add(raunds.getSeasonWars().get(clanName));
        }
        defenderClanList.sort((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime()));


        for (String clanName : defenderClans) {
            for (Attacker userAttacker : raunds.getSeasonWars().get(clanName).getAttackers()) {
                userAttackersTag.add(userAttacker.getTag());
            }
        }

        Map<String, Map<String, List<Attacker>>> attackerMap = new HashMap<>();

        for (String clanName : defenderClans) { // по кланам

            for (Attacker userAttacker : raunds.getSeasonWars().get(clanName).getAttackers()) { // кто участвовал в этой войне

                if (attackerMap.get(userAttacker.getName()) == null) {
                    List<Attacker> ata = new ArrayList<>();
                    ata.add(userAttacker);

                    Map<String, List<Attacker>> map = new HashMap<>();
                    map.put(clanName, ata);

                    attackerMap.put(userAttacker.getName(), map);
                }

                if (attackerMap.get(userAttacker.getName()).get(clanName) == null) {

                    List<Attacker> ata = new ArrayList<>();
                    ata.add(userAttacker);

                    attackerMap.get(userAttacker.getName()).put(clanName, ata);
                }

                if (!attackerMap.get(userAttacker.getName()).get(clanName).contains(userAttacker)) {
                    attackerMap.get(userAttacker.getName()).get(clanName).add(userAttacker);
                }
            }
        }

        SeasonDetailsDTO seasonDetailsDTO = new SeasonDetailsDTO();
        seasonDetailsDTO.setUsersAttack(sortMap(attackerMap));
        seasonDetailsDTO.setDefenderClans(defenderClanList);
        seasonDetailsDTO.setSeason(raunds.getSeason());
        return seasonDetailsDTO;
    }

    private Map<String, Map<String, List<Attacker>>> sortMap(Map<String, Map<String, List<Attacker>>> attackerMap) {
        return attackerMap.entrySet().stream().sorted((e1, e2) ->
                Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void execute() {
//        Map<String, List<String>> seasonAndWars = seasonService.getAllCurrentWarTagByClanTag();

        Season season = new Season();
        season.setName("2018-11");
        season.setClanTag("#8P2RCUVR");
        seasonService.saveSeason(season);

        List<String> wars = new ArrayList<>();

        wars.add("#28GU9QPLP");
        wars.add("#28GU9LRPP");
        wars.add("#28GU9LCUU");
        wars.add("#28GU9LJGG"); // +

        wars.add("#28R0R8CGP");
        wars.add("#28R0R8UUG");
        wars.add("#28R0R9RPU");
        wars.add("#28R0R9JR8");

        wars.add("#28R9U8028");
        wars.add("#28R9U8P00");
        wars.add("#28R9U8YPP");
        wars.add("#28R9U8LGG");

        wars.add("#28RQ2RYQ8");
        wars.add("#28RQ2RLCL");
        wars.add("#28RQ2RG2J");
        wars.add("#28RQ2RRL0");

        wars.add("#28RJY2CUP");
        wars.add("#28RJY2V8G");
        wars.add("#28RJY82C8");
        wars.add("#28RJY892L");

        Map<Season, List<String>> seasonAndWars = new HashMap<>();
        seasonAndWars.put(season, wars);


        for (Season s : seasonAndWars.keySet()) {
            for (String warTag : seasonAndWars.get(s)) {

                Raund raund = roundService.getRoundByTag(warTag);
                if (raund.getState().equals("warEnded")) {
                    raund.setSeason(seasonService.getSeasonByNameAndClanTag(s.getName(), s.getClanTag()));
                    raund.setWarTag(warTag);
                    roundService.save(raund);
                }
            }
        }

//        List<Raund> raunds = roundService.getAllRaundsByClanTag("#8P2RCUVR");
//        System.out.println(raunds.size());
//        getAttacker();
    }

    private SeasonDTO getAttacker(String clanTag, String seasonName) {
//        #8P2RCUVR     .flashback.  +++
//        #2GQ0ULPU	    Malaysia  ---  !
//        #8QRYVUVQ 	MentalistMafia +++ !
//        #CQG00CC	    Devil's Family ---  !
//        #99VR9L8R	    GEEK QUEBEC  +++  !
//        #2JL2RLCC	    Sek Gelar ---  !


        List<Raund> raunds = roundService.getAllRaundsByClanTag(clanTag, seasonName);

//        меняем местами клан - опонент
        if (raunds.isEmpty()) {
            raunds = roundService.getAllRaundsByClanTagInverce(clanTag, seasonName);
        }

        Map<String, DefenderClan> result = new HashMap<>();
        SeasonDTO seasonDTO = new SeasonDTO();
        seasonDTO.setSeason(seasonName);

        for (Raund raund : raunds) {


            Clan opponent = raund.getOpponent();
            Clan defenderClan = raund.getClan();
            DefenderClan attackerClan = new DefenderClan();
            attackerClan.setAttackerName(opponent.getName());
            attackerClan.setDefenderName(defenderClan.getName());
            attackerClan.setAttackerClanLevel(opponent.getClanLevel());
            attackerClan.setDefenderClanLevel(defenderClan.getClanLevel());
            attackerClan.setAttackerStars(opponent.getStars());
            attackerClan.setDefenderStars(defenderClan.getStars());
            attackerClan.setStartTime(raund.getStartTime());
            attackerClan.setAttackerBadgeUrl(opponent.getBadgeUrls().getSmall());
            attackerClan.setDefenderBadgeUrl(defenderClan.getBadgeUrls().getSmall());
            attackerClan.setDefenderClanTag(defenderClan.getTag().replaceAll("#", "%23"));

            seasonDTO.setName(opponent.getName());
            List<Attacker> attackers = new ArrayList<>();
            attackerClan.setAttackers(attackers);

            List<Member> members = opponent.getMembers();
            for (Member member : members) {

                if (member.getAttacks().size() > 0) {
                    Attacker attacker = new Attacker();
                    attacker.setName(member.getName());
                    attacker.setTag(member.getTag());
                    attacker.setTownhallLevel(member.getTownhallLevel());
                    attacker.setMapPosition(member.getMapPosition());
                    attacker.setImgUrl(getImageByTownhallLevel(member.getTownhallLevel()));
                    attackers.add(attacker);

                    List<Defender> defenders = new ArrayList<>();
                    for (Attack attack : member.getAttacks()) {
//                      Может быть null
                        Defender defender = getDefenderByAttackerTag(attack.getDefenderTag(), raund.getClan());
                        defender.setId(attack.getId());
                        defender.setDestructionPercentage(attack.getDestructionPercentage());
                        defender.setStars(attack.getStars());
                        defender.setImgUrlStars(getImageByStars(attack.getStars()));
                        defenders.add(defender);
                    }
                    attacker.setDefenders(defenders);
                }
            }
            result.put(raund.getClan().getName(), attackerClan);
        }
        seasonDTO.setSeasonWars(result);
        return seasonDTO;
    }

    private String getImageByTownhallLevel(Integer townhallLevel) {
        switch (townhallLevel) {
            case 12:
                return "/img/Town_Hall12.png";
            case 11:
                return "/img/Town_Hall11.png";
            case 10:
                return "/img/Town_Hall10.png";
            case 9:
                return "/img/Town_Hall9.png";
            case 8:
                return "/img/Town_Hall8.png";
            case 7:
                return "/img/Town_Hall7.png";
            case 6:
                return "/img/Town_Hall6.png";
            case 5:
                return "/img/Town_Hall6.png";
            case 4:
                return "/img/Town_Hall6.png";
            default:
                return "";
        }
    }

    private String getImageByStars(Integer stars) {
        switch (stars) {
            case 3:
                return "/img/3_star.png";
            case 2:
                return "/img/2_star.png";
            case 1:
                return "/img/1_star.png";
            case 0:
                return "/img/0_star.png";
            default:
                return "";
        }
    }

    private Defender getDefenderByAttackerTag(String defenderTag, Clan clan) {
        List<Member> members = clan.getMembers();
//        for (Member member : members) {
//            if (member.getOpponentAttacks() > 0) {
//                for (Attack attack : member.getAttacks()) {
//                    if (attack.getAttackerTag().equals(defenderTag)) {
//                        Defender defender = new Defender();
//                        defender.setName(member.getName());
//                        defender.setTownhallLevel(member.getTownhallLevel());
//                        defender.setMapPosition(member.getMapPosition());
//                        defender.setImgUrl(getImageByTownhallLevel(member.getTownhallLevel()));
//                        return defender;
//                    }
//                }
//            }
//        }

        for (Member member : members) {
            if (member.getTag().equals(defenderTag)) {
                Defender defender = new Defender();
                defender.setName(member.getName());
                defender.setTownhallLevel(member.getTownhallLevel());
                defender.setMapPosition(member.getMapPosition());
                defender.setImgUrl(getImageByTownhallLevel(member.getTownhallLevel()));
                return defender;
            }
        }
        return null;
    }
}
