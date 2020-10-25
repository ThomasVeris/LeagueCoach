package be.vdab.commands;

import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class GetMatchData {
    public static int getParticipantId(String concatenatedAccountName, int matchIndex) throws FileNotFoundException {
        String summonerJson = "./src/main/resources/json/" + concatenatedAccountName +  "/SummonerInfo.json";
        Object accountIdObj = new JsonParser().parse(new FileReader(summonerJson));
        String accountID = JsonPath.read(accountIdObj, "$.accountId").toString().replace("\"", "");
        int participantId;

        for (participantId = 0; participantId < 10; participantId++) {
            String matchJson = "./src/main/resources/json/" + concatenatedAccountName +  "/Match" + matchIndex + ".json";
            Object matchObj = new JsonParser().parse(new FileReader(matchJson));
            String participantAccountId = JsonPath.read(matchObj, "$.participantIdentities[" + participantId
                    + "].player.currentAccountId").toString().replace("\"", "");
            if (participantAccountId.equals(accountID)){
                break;}
        }
        return participantId + 1;
    }

    public static Map<String, Number> getMatchData(String concatenatedAccountName, int matchIndex, int participantId) throws FileNotFoundException {
        String matchJson = "./src/main/resources/json/" + concatenatedAccountName +  "/Match" + matchIndex + ".json";
        Object matchObj = new JsonParser().parse(new FileReader(matchJson));
        Map<String, Number> participantStats = new HashMap<>();

        String jsonKills = JsonPath.read(matchObj, "$.participants[" + participantId + "].stats.kills").toString();
        int participantKills = Integer.parseInt(jsonKills);
        participantStats.put("Kills", participantKills);

        String jsonDeaths = JsonPath.read(matchObj, "$.participants[" + participantId + "].stats.deaths").toString();
        int participantDeaths = Integer.parseInt(jsonDeaths);
        participantStats.put("Deaths", participantDeaths);

        String jsonAssists = JsonPath.read(matchObj, "$.participants[" + participantId + "].stats.assists").toString();
        int participantAssists = Integer.parseInt(jsonAssists);
        participantStats.put("Assists", participantAssists);

        float participantKDARatio = (participantKills + participantAssists) / (float) participantDeaths;
        participantStats.put("KDA Ratio", participantKDARatio);




        return participantStats;

    }
}
