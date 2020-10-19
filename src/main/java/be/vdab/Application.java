package be.vdab;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static be.vdab.Keys.APIKEY;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        System.out.println("Please enter your summoner name:");
        Scanner inputScanner = new Scanner(System.in);
        String accountName = inputScanner.nextLine();
        String concatenatedAccountName = accountName.replaceAll("\\s+", "");
        getSummonerInfo(concatenatedAccountName);
        getMatchListBySummoner(concatenatedAccountName);
        getMatchByID(concatenatedAccountName);
    }

    public static void getSummonerInfo(String concatenatedAccountName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet summonerInfo = new HttpGet(
                "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + concatenatedAccountName);
        summonerInfo.setHeader("X-Riot-Token", APIKEY);

        try (CloseableHttpResponse httpResponse = httpClient.execute(summonerInfo)) {
            HttpEntity entitySummonerInfo = httpResponse.getEntity();
            if (entitySummonerInfo != null) {
                Path summonerFolder = Paths.get("./src/main/java/be/vdab/json/" + concatenatedAccountName);
                Path newPath = Files.createDirectory(summonerFolder);
                try (InputStream inputStream = entitySummonerInfo.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            "./src/main/java/be/vdab/json/" + concatenatedAccountName +  "/SummonerInfo.json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }

    public static void getMatchListBySummoner(String concatenatedAccountName) throws IOException {
        String accountID = "VjJel5MqVGkeEo58RbTk-E0QLZM6Y_JJzQXPW7GSXrsaEdw";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet leagueMatches = new HttpGet("https://euw1.api.riotgames.com/lol/match/v4/matchlists/by-account/"
                + accountID);
        leagueMatches.setHeader("X-Riot-Token", APIKEY);

        try (CloseableHttpResponse httpResponse = httpClient.execute(leagueMatches)) {
            HttpEntity entityMatches = httpResponse.getEntity();
            if (entityMatches != null) {
                try (InputStream inputStream = entityMatches.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            "./src/main/java/be/vdab/json/" + concatenatedAccountName + "/MatchList.json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }

    public static void getMatchByID(String concatenatedAccountName) throws IOException {
        String matchId = "4874361787";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet matchById = new HttpGet("https://euw1.api.riotgames.com/lol/match/v4/matches/"
                + matchId);
        matchById.setHeader("X-Riot-Token", APIKEY);

        try (CloseableHttpResponse httpResponse = httpClient.execute(matchById)) {
            HttpEntity entityMatches = httpResponse.getEntity();
            if (entityMatches != null) {
                try (InputStream inputStream = entityMatches.getContent()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            "./src/main/java/be/vdab/json/" + concatenatedAccountName + "/" + matchId + ".json");
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
        }
    }
}
