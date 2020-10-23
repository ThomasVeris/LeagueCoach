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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SummonerRepository {
    public static void getSummonerInfo(String concatenatedAccountName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet summonerInfo = new HttpGet(
                "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + concatenatedAccountName);
        summonerInfo.setHeader("X-Riot-Token", AccessKey.ACCESS_KEY.getKeyValue());

        try (CloseableHttpResponse httpResponse = httpClient.execute(summonerInfo)) {
            HttpEntity entitySummonerInfo = httpResponse.getEntity();
            if (entitySummonerInfo != null) {
                Path summonerFolder = Paths.get("./src/main/resources/json/" + concatenatedAccountName);
                Path newPath = Files.createDirectories(summonerFolder);
                try (InputStream inputStream = entitySummonerInfo.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            "./src/main/resources/json/" + concatenatedAccountName + "/SummonerInfo.json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }
}
