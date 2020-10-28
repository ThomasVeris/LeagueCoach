package be.vdab.repository;

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
import static be.vdab.domain.enums.RiotURL.MATCH_LIST_BY_ACCOUNT_ID;

public class MatchListRepository {
    private MatchListRepository() {
    }

    public static void getMatchListBySummoner(String concatenatedAccountName) throws IOException {

        String jsonFile = JSON_DIRECTORY.getPath() + concatenatedAccountName +  "/SummonerInfo.json";
        Object obj = JsonParser.parseReader(new FileReader(jsonFile));
        String accountID = JsonPath.read(obj, "$.accountId").toString().replace("\"", "");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet leagueMatches = new HttpGet(MATCH_LIST_BY_ACCOUNT_ID.getUrl() + accountID);
        leagueMatches.setHeader("X-Riot-Token", ACCESS_KEY.getKeyValue());

        try (CloseableHttpResponse httpResponse = httpClient.execute(leagueMatches)) {
            HttpEntity entityMatches = httpResponse.getEntity();
            if (entityMatches != null) {
                try (InputStream inputStream = entityMatches.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            JSON_DIRECTORY.getPath() + concatenatedAccountName + "/MatchList.json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }
}
