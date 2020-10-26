package be.vdab.domain.enums;

public enum RiotURL {
    SUMMONER_BY_NAME("https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"),
    MATCH_LIST_BY_ACCOUNT_ID("https://euw1.api.riotgames.com/lol/match/v4/matchlists/by-account/"),
    MATCH_BY_GAME_ID("https://euw1.api.riotgames.com/lol/match/v4/matches/");

    private final String url;

    RiotURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
