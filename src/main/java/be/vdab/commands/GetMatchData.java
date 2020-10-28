package be.vdab.commands;

import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import static be.vdab.domain.enums.Directory.JSON_DIRECTORY;

public class GetMatchData {
    private GetMatchData() {
    }

    public static int getParticipantId(String concatenatedAccountName, int matchIndex) throws FileNotFoundException {
        String summonerJson = JSON_DIRECTORY.getPath() + concatenatedAccountName +  "/SummonerInfo.json";
        Object accountIdObj = JsonParser.parseReader(new FileReader(summonerJson));
        String accountID = JsonPath.read(accountIdObj, "$.accountId")
                                   .toString()
                                   .replace("\"", "");
        int participantId;

        for (participantId = 0; participantId < 10; participantId++) {
            String matchJson = JSON_DIRECTORY.getPath() + concatenatedAccountName +  "/Match" + matchIndex + ".json";
            Object matchObj = JsonParser.parseReader(new FileReader(matchJson));
            String participantAccountId = JsonPath
                    .read(matchObj, "$.participantIdentities[" + participantId + "].player.currentAccountId")
                    .toString()
                    .replace("\"", "");
            if (participantAccountId.equals(accountID)){
                break;}
        }
        return participantId + 1;
    }

    public static Map<String, Number> getMatchData(String concatenatedAccountName, int matchIndex, int participantId)
            throws FileNotFoundException {

        String matchJson = JSON_DIRECTORY.getPath() + concatenatedAccountName +  "/Match" + matchIndex + ".json";
        Object matchObj = JsonParser.parseReader(new FileReader(matchJson));
        Map<String, Number> participantStats = new HashMap<>();

        String statSelector = "$.participants[" + participantId + "].stats";

        String jsonKills = JsonPath.read(matchObj, statSelector + ".kills").toString();
        int participantKills = Integer.parseInt(jsonKills);
        participantStats.put("Kills", participantKills);

        String jsonDeaths = JsonPath.read(matchObj, statSelector +  ".deaths").toString();
        int participantDeaths = Integer.parseInt(jsonDeaths);
        participantStats.put("Deaths", participantDeaths);

        String jsonAssists = JsonPath.read(matchObj, statSelector +  ".assists").toString();
        int participantAssists = Integer.parseInt(jsonAssists);
        participantStats.put("Assists", participantAssists);

        float participantKDARatio = (participantKills + participantAssists) / (float) participantDeaths;
        participantStats.put("KDA Ratio", participantKDARatio);




        return participantStats;

    }
}
