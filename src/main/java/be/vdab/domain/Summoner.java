package be.vdab.domain;

import java.util.Date;

public class Summoner {
    private String summonerId;
    private String accountId;
    private String puuId;
    private String summonerName;
    private int profileIconId;
    private Date revisionDate;
    private int summonerLevel;

    public String getSummonerId() {
        return summonerId;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public Summoner setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
        return this;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public Summoner setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
        return this;
    }

    public int getSummonerLevel() {
        return summonerLevel;
    }

    public Summoner setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
        return this;
    }

    public Summoner setSummonerId(String summonerId) {
        this.summonerId = summonerId;
        return this;
    }

    public String getAccountId() {
        return accountId;
    }

    public Summoner setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getPuuId() {
        return puuId;
    }

    public Summoner setPuuId(String puuId) {
        this.puuId = puuId;
        return this;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public Summoner setSummonerName(String summonerName) {
        this.summonerName = summonerName;
        return this;
    }
}
