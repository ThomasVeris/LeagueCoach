package be.vdab.repository;

import be.vdab.domain.enums.AccessKey;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class MatchListRepository {


    public static void getMatchListBySummoner(String concatenatedAccountName) throws IOException {

        String jsonFile = "./src/main/resources/json/" + concatenatedAccountName +  "/SummonerInfo.json";
        Object obj = new JsonParser().parse(new FileReader(jsonFile));
        String accountID = JsonPath.read(obj, "$.accountId").toString().replace("\"", "");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet leagueMatches = new HttpGet("https://euw1.api.riotgames.com/lol/match/v4/matchlists/by-account/"
                + accountID);
        leagueMatches.setHeader("X-Riot-Token", AccessKey.ACCESS_KEY.getKeyValue());

        try (CloseableHttpResponse httpResponse = httpClient.execute(leagueMatches)) {
            HttpEntity entityMatches = httpResponse.getEntity();
            if (entityMatches != null) {
                try (InputStream inputStream = entityMatches.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            "./src/main/resources/json/" + concatenatedAccountName + "/MatchList.json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }
}
