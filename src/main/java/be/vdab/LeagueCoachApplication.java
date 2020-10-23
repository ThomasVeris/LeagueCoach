package be.vdab;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

import static be.vdab.commands.GetMatchCommand.getMatchIndex;
import static be.vdab.commands.GetMatchDataCommand.getParticipantId;
import static be.vdab.commands.GetSummonerNameCommand.enterSummonerName;
import static be.vdab.repository.MatchListRepository.getMatchListBySummoner;
import static be.vdab.repository.MatchRepository.getMatchByID;
import static be.vdab.repository.SummonerRepository.getSummonerInfo;

@SpringBootApplication
public class LeagueCoachApplication {

    public static void main(String[] args) throws IOException {
        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new GsonJsonProvider();
            private final MappingProvider mappingProvider = new GsonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });



        String accountName = enterSummonerName();
        int matchId = getMatchIndex();

        getSummonerInfo(accountName);
        getMatchListBySummoner(accountName);
        getMatchByID(accountName, matchId);

        int participantId = getParticipantId(accountName, matchId);
        System.out.println("The participant ID of the given summoner during this match was: ");
        System.out.println(participantId);
    }








}
