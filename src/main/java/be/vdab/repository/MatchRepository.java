package be.vdab.repository;

import be.vdab.domain.enums.AccessKey;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MatchRepository {
    public static void getMatchByID(String concatenatedAccountName) throws IOException {
        String matchId = "4874361787";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet matchById = new HttpGet("https://euw1.api.riotgames.com/lol/match/v4/matches/"
                + matchId);
        matchById.setHeader("X-Riot-Token", AccessKey.ACCESS_KEY.getKeyValue());

        try (CloseableHttpResponse httpResponse = httpClient.execute(matchById)) {
            HttpEntity entityMatches = httpResponse.getEntity();
            if (entityMatches != null) {
                try (InputStream inputStream = entityMatches.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            "./src/main/resources/json/" + concatenatedAccountName + "/" + matchId + ".json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }
}
