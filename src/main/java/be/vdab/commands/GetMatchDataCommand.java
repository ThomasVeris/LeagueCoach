package be.vdab.commands;

import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class GetMatchDataCommand {
    public static int getParticipantId(String concatenatedAccountName, int matchIndex) throws FileNotFoundException {
        String summonerJson = "./src/main/resources/json/" + concatenatedAccountName +  "/SummonerInfo.json";
        Object accountIdObj = new JsonParser().parse(new FileReader(summonerJson));
        String accountID = JsonPath.read(accountIdObj, "$.accountId").toString().replace("\"", "");
        int participantId;

        for (participantId = 0; participantId < 10; participantId++) {
            String matchJson = "./src/main/resources/json/" + concatenatedAccountName +  "/Match" + matchIndex + ".json";
            Object matchObj = new JsonParser().parse(new FileReader(matchJson));
            String participantAccountId = JsonPath.read(matchObj, "$.participantIdentities[" + participantId + "].player.currentAccountId").toString().replace("\"", "");
            if (participantAccountId.equals(accountID)){
                break;}
        }
        return participantId + 1;
    }

    public void getMatchData(String concatenatedAccountName, int matchIndex){

    }
}
