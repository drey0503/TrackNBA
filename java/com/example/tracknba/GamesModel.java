package com.example.tracknba;

class GamesModel {
    String awayTeamName, homeTeamName,awayTeamRecord, homeTeamRecord,
            awayTeamFinalScore, homeTeamFinalScore, gameClock, awayTeamAwayRecord, awayTeamHomeRecord, awayTeamTotalRecord,
            homeTeamAwayRecord, homeTeamHomeRecord, homeTeamTotalRecord;
    String[][] awayScoreLines;
    String[][] homeScoreLines;
    int awayTeamImageID, homeTeamImageID, totalQuarters, gamePeriods;
    boolean halftimeStatus, gamePeriodStatus;

    public GamesModel(String awayTeamName, String homeTeamName, String awayTeamRecord,
                      String homeTeamRecord, String[][] awayTeamScore, String[][] homeTeamScore,
                      String awayTeamFinalScore, String homeTeamFinalScore, int awayTeamImageID, int homeTeamImageID, int totalQuarters,
                      String gameClock, boolean halftimeStatus, boolean gamePeriodStatus, int gamePeriods, String awayTeamAwayRecord,
                      String awayTeamHomeRecord, String awayTeamTotalRecord, String homeTeamAwayRecord, String homeTeamHomeRecord, String homeTeamTotalRecord) {
        this.awayTeamName = awayTeamName;
        this.homeTeamName = homeTeamName;
        this.awayTeamRecord = awayTeamRecord;
        this.homeTeamRecord = homeTeamRecord;
        this.awayScoreLines = awayTeamScore;
        this.homeScoreLines = homeTeamScore;
        this.awayTeamFinalScore = awayTeamFinalScore;
        this.homeTeamFinalScore = homeTeamFinalScore;
        this.awayTeamImageID = awayTeamImageID;
        this.homeTeamImageID = homeTeamImageID;
        this.totalQuarters = totalQuarters;
        this.gameClock = gameClock;
        this.halftimeStatus = halftimeStatus;
        this.gamePeriodStatus = gamePeriodStatus;
        this.gamePeriods = gamePeriods;
        this.awayTeamAwayRecord = awayTeamAwayRecord;
        this.awayTeamHomeRecord = awayTeamHomeRecord;
        this.awayTeamTotalRecord = awayTeamTotalRecord;
        this.homeTeamAwayRecord = homeTeamAwayRecord;
        this.homeTeamHomeRecord = homeTeamHomeRecord;
        this.homeTeamTotalRecord = homeTeamTotalRecord;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamRecord() {
        return awayTeamRecord;
    }

    public String getHomeTeamRecord() {
        return homeTeamRecord;
    }

    public String[][] getAwayTeamScore() {
        return awayScoreLines;
    }

    public String[][] getHomeTeamScore() {
        return homeScoreLines;
    }

    public String getAwayTeamFinalScore() {
        return awayTeamFinalScore;
    }

    public String getHomeTeamFinalScore() {
        return homeTeamFinalScore;
    }

    public int getAwayTeamImageID() {
        return awayTeamImageID;
    }

    public int getHomeTeamImageID() {
        return homeTeamImageID;
    }

    public int getTotalQuarters() {
        return totalQuarters;
    }

    public String getGameClock() {
        return gameClock;
    }

    public int getGamePeriods() {
        return gamePeriods;
    }

    public boolean isHalftimeStatus() {
        return halftimeStatus;
    }

    public boolean isGamePeriodStatus() {
        return gamePeriodStatus;
    }

    public String getAwayTeamAwayRecord() {
        return awayTeamAwayRecord;
    }

    public String getAwayTeamHomeRecord() {
        return awayTeamHomeRecord;
    }

    public String getAwayTeamTotalRecord() {
        return awayTeamTotalRecord;
    }

    public String getHomeTeamAwayRecord() {
        return homeTeamAwayRecord;
    }

    public String getHomeTeamHomeRecord() {
        return homeTeamHomeRecord;
    }

    public String getHomeTeamTotalRecord() {
        return homeTeamTotalRecord;
    }
}
