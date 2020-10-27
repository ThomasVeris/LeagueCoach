package be.vdab.repository;

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

import static be.vdab.domain.enums.AccessKey.ACCESS_KEY;
import static be.vdab.domain.enums.Directory.JSON_DIRECTORY;
import static be.vdab.domain.enums.RiotURL.SUMMONER_BY_NAME;

public class SummonerRepository {
    public static void getSummonerInfo(String concatenatedAccountName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet summonerInfo = new HttpGet(
                SUMMONER_BY_NAME.getUrl() + concatenatedAccountName);
        summonerInfo.setHeader("X-Riot-Token", ACCESS_KEY.getKeyValue());

        try (CloseableHttpResponse httpResponse = httpClient.execute(summonerInfo)) {
            HttpEntity entitySummonerInfo = httpResponse.getEntity();
            if (entitySummonerInfo != null) {
                Path summonerFolder = Paths.get(JSON_DIRECTORY.getPath() + concatenatedAccountName);
                Path newPath = Files.createDirectories(summonerFolder);
                try (InputStream inputStream = entitySummonerInfo.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            JSON_DIRECTORY.getPath() + concatenatedAccountName + "/SummonerInfo.json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }
}
