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

import static be.vdab.domain.enums.AccessKey.ACCESS_KEY;
import static be.vdab.domain.enums.Directory.JSON_DIRECTORY;
import static be.vdab.domain.enums.RiotURL.MATCH_BY_GAME_ID;

public class MatchRepository {
    public static void getMatchByID(String concatenatedAccountName, int matchIndex) throws IOException {

        String jsonFile = JSON_DIRECTORY.getPath() + concatenatedAccountName +  "/MatchList.json";
        Object obj = JsonParser.parseReader(new FileReader(jsonFile));
        String matchId = JsonPath.read(obj, "$.matches.[" + matchIndex + "].gameId").toString();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet matchById = new HttpGet(MATCH_BY_GAME_ID.getUrl() + matchId);
        matchById.setHeader("X-Riot-Token", ACCESS_KEY.getKeyValue());

        try (CloseableHttpResponse httpResponse = httpClient.execute(matchById)) {
            HttpEntity entityMatches = httpResponse.getEntity();
            if (entityMatches != null) {
                try (InputStream inputStream = entityMatches.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            JSON_DIRECTORY.getPath() + concatenatedAccountName + "/Match" + matchIndex + ".json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }
}
