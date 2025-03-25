package com.example.tracknba;

class PlayerGamesModel {
    String playerTeamId, homeTeam, awayTeam, min, gameDate, pts, reb, ast, stl, blk, pf, to, fg_pct, fg3_pct, ft_pct;
    int homeTeamScore, awayTeamScore;

    public PlayerGamesModel(String playerTeamId, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, String gameDate, String min, String pts, String reb, String ast, String stl, String blk, String pf, String to, String fg_pct, String fg3_pct, String ft_pct) {
        this.playerTeamId = playerTeamId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.gameDate = gameDate;
        this.min = min;
        this.pts = pts;
        this.reb = reb;
        this.ast = ast;
        this.stl = stl;
        this.blk = blk;
        this.pf = pf;
        this.to = to;
        this.fg_pct = fg_pct;
        this.fg3_pct = fg3_pct;
        this.ft_pct = ft_pct;
    }

    public String getPlayerTeamId() {
        return playerTeamId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getMin() {
        return min;
    }
    public String getGameDate() {
        return gameDate;
    }

    public String getPts() {
        return pts;
    }

    public String getReb() {
        return reb;
    }

    public String getAst() {
        return ast;
    }

    public String getStl() {
        return stl;
    }

    public String getBlk() {
        return blk;
    }

    public String getPf() {
        return pf;
    }

    public String getTo() {
        return to;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public String getFg_pct() {
        return fg_pct;
    }

    public String getFg3_pct() {
        return fg3_pct;
    }

    public String getFt_pct() {
        return ft_pct;
    }
}
