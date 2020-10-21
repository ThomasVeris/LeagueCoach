package be.vdab;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static be.vdab.commands.SummonerNameCommand.enterSummonerName;
import static be.vdab.repository.MatchListRepository.getMatchListBySummoner;
import static be.vdab.repository.MatchRepository.getMatchByID;
import static be.vdab.repository.SummonerRepository.getSummonerInfo;

@SpringBootApplication
public class LeagueCoachApplication {

    public static void main(String[] args) throws IOException {


       String accountName = enterSummonerName();

        getSummonerInfo(accountName);
        getMatchListBySummoner(accountName);
        getMatchByID(accountName);
    }








}
