package com.example.tracknba;

public class ViewPagerModel {
    String rank, teamName, wins, losses, logo;

    public ViewPagerModel(String rank, String teamName, String wins, String losses, String logo) {
        this.rank = rank;
        this.teamName = teamName;
        this.wins = wins;
        this.losses = losses;
        this.logo = logo;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
