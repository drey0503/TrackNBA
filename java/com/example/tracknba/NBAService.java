package com.example.tracknba;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NBAService {

    public static final String QUERY_FULL_NAME = "https://api-nba-v1.p.rapidapi.com/teams?search=";
    public static final String QUERY_TEAM_CODE = "https://api-nba-v1.p.rapidapi.com/teams?code=";
    public static final String QUERY_TEAM_STANDINGS = "https://api-nba-v1.p.rapidapi.com/standings?league=standard&season=2022&team=";
    public static final String QUERY_PLAYER_INFO = "https://www.balldontlie.io/api/v1/players?search=";
    public static final String PLAYER_IMAGE_ID = "https://data.nba.net/data/10s/prod/v1/2022/players.json";
    public static final String PLAYER_STATS = "https://www.balldontlie.io/api/v1/stats?seasons[]=2022&per_page=100&player_ids[]=";
    public static final String QUERY_GAMES = "https://api-nba-v1.p.rapidapi.com/games?league=standard&season=2022&team=";
    public static final String QUERY_STANDINGS = "https://api-nba-v1.p.rapidapi.com/standings?league=standard&season=2022";
    public static final String PLAYER_IMAGE = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/";
    private static final DecimalFormat df = new DecimalFormat("0.0");
    Context context;
    String teamID;
    String teamInfo;
    String teamFullName;
    String teamNickName;
    String playerFullName;
    String playerTeam;
    String playerImageID;
    String[] awayTeams = new String[10];
    String[] homeTeams = new String[10];
    String[] playerTeamId = new String[10];
    String[][] awayScoreLines = new String[10][6];
    String[][] homeScoreLines = new String[10][6];
    String[] awayFinalScores = new String[10];
    String[] homeFinalScores = new String[10];
    String[] gameClock = new String[10];
    String[] gameDate = new String[10];
    String[] homeTeamTotalRecord = new String[10];
    String[] homeTeamHomeRecord = new String[10];
    String[] homeTeamAwayRecord = new String[10];
    String[] awayTeamTotalRecord = new String[10];
    String[] awayTeamHomeRecord = new String[10];
    String[] awayTeamAwayRecord = new String[10];
    boolean[] gamePeriodStatus = new boolean[10];
    boolean[] halftimeStatus = new boolean[10];
    int[] gamePeriods = new int[10];
    int[] totalQuarters = new int[10];
    String[] min = new String[10];
    String[] reb = new String[10];
    String[] ast = new String[10];
    String[] blk = new String[10];
    String[] stl = new String[10];
    String[] pf = new String[10];
    String[] to = new String[10];
    String[] pts = new String[10];
    String[] fg_pct = new String[10];
    String[] fg3_pct = new String[10];
    String[] ft_pct = new String[10];
    int[] homeTeamScore = new int[10];
    int[] awayTeamScore = new int[10];

    protected int playerID, p1_games;
    protected double p1_points, p1_rebounds, p1_assists, p1_steals, p1_blocks;
    protected double p1_ppg, p1_rpg, p1_apg, p1_spg, p1_bpg;
    Map<String, String> teamCode = new HashMap<>();
    Map<String, String> teamAbbreviation = new HashMap<>();
    Map<String, String> standings = new HashMap<>();
    Map<String, String[]> teamStandings = new HashMap<>();
    ArrayList<Integer> gameIds;
    ArrayList<String> playerList = new ArrayList<>();

    public NBAService() {
        teamAbbreviation.put("1", "ATL");
        teamAbbreviation.put("2", "BOS");
        teamAbbreviation.put("3", "BKN");
        teamAbbreviation.put("4", "CHA");
        teamAbbreviation.put("5", "CHI");
        teamAbbreviation.put("6", "CLE");
        teamAbbreviation.put("7", "DAL");
        teamAbbreviation.put("8", "DEN");
        teamAbbreviation.put("9", "DET");
        teamAbbreviation.put("10", "GSW");
        teamAbbreviation.put("11", "HOU");
        teamAbbreviation.put("12", "IND");
        teamAbbreviation.put("13", "LAC");
        teamAbbreviation.put("14", "LAL");
        teamAbbreviation.put("15", "MEM");
        teamAbbreviation.put("16", "MIA");
        teamAbbreviation.put("17", "MIL");
        teamAbbreviation.put("18", "MIN");
        teamAbbreviation.put("19", "NOP");
        teamAbbreviation.put("20", "NYK");
        teamAbbreviation.put("21", "OKC");
        teamAbbreviation.put("22", "ORL");
        teamAbbreviation.put("23", "PHI");
        teamAbbreviation.put("24", "PHX");
        teamAbbreviation.put("25", "POR");
        teamAbbreviation.put("26", "SAC");
        teamAbbreviation.put("27", "SAS");
        teamAbbreviation.put("28", "TOR");
        teamAbbreviation.put("29", "UTA");
        teamAbbreviation.put("30", "WAS");

        teamAbbreviation.put("ATL", "hawks");
        teamAbbreviation.put("BOS", "celtics");
        teamAbbreviation.put("BKN", "nets");
        teamAbbreviation.put("CHA", "hornets");
        teamAbbreviation.put("CHI", "bulls");
        teamAbbreviation.put("CLE", "cavaliers");
        teamAbbreviation.put("DAL", "mavericks");
        teamAbbreviation.put("DEN", "nuggets");
        teamAbbreviation.put("DET", "pistons");
        teamAbbreviation.put("GSW", "warriors");
        teamAbbreviation.put("HOU", "rockets");
        teamAbbreviation.put("IND",  "pacers");
        teamAbbreviation.put("LAC", "clippers");
        teamAbbreviation.put("LAL", "lakers");
        teamAbbreviation.put("MEM", "grizzlies");
        teamAbbreviation.put("MIA", "heat");
        teamAbbreviation.put("MIL", "bucks");
        teamAbbreviation.put("MIN", "timberwolves");
        teamAbbreviation.put("NOP", "pelicans");
        teamAbbreviation.put("NYK", "knicks");
        teamAbbreviation.put("OKC", "thunder");
        teamAbbreviation.put("ORL", "magic");
        teamAbbreviation.put("PHI", "76ers");
        teamAbbreviation.put("PHX", "suns");
        teamAbbreviation.put("POR", "trail blazers");
        teamAbbreviation.put("SAC", "kings");
        teamAbbreviation.put("SAS", "spurs");
        teamAbbreviation.put("TOR", "raptors");
        teamAbbreviation.put("UTA", "jazz");
        teamAbbreviation.put("WAS", "wizards");
    }

    public NBAService(Context context) {
        this.context = context;
        teamCode.put("Atlanta Hawks", "hawks");
        teamCode.put("Boston Celtics", "celtics");
        teamCode.put("Brooklyn Nets", "nets");
        teamCode.put("Charlotte Hornets", "hornets");
        teamCode.put("Chicago Bulls", "bulls");
        teamCode.put("Cleveland Cavaliers", "cavaliers");
        teamCode.put("Dallas Mavericks", "mavericks");
        teamCode.put("Denver Nuggets", "nuggets");
        teamCode.put("Detroit Pistons", "pistons");
        teamCode.put("Golden State Warriors", "warriors");
        teamCode.put("Houston Rockets", "rockets");
        teamCode.put("Indiana Pacers", "pacers");
        teamCode.put("LA Clippers", "clippers");
        teamCode.put("Los Angeles Lakers", "lakers");
        teamCode.put("Memphis Grizzlies", "grizzlies");
        teamCode.put("Miami Heat", "heat");
        teamCode.put("Milwaukee Bucks", "bucks");
        teamCode.put("Minnesota Timberwolves", "timberwolves");
        teamCode.put("New Orleans Pelicans", "pelicans");
        teamCode.put("New York Knicks", "knicks");
        teamCode.put("Oklahoma City Thunder", "thunder");
        teamCode.put("Orlando Magic", "magic");
        teamCode.put("Philadelphia 76ers", "76ers");
        teamCode.put("Phoenix Suns", "suns");
        teamCode.put("Portland Trailblazers", "trail blazers");
        teamCode.put("Sacramento Kings", "kings");
        teamCode.put("San Antonio Spurs", "spurs");
        teamCode.put("Toronto Raptors", "raptors");
        teamCode.put("Utah Jazz", "jazz");
        teamCode.put("Washington Wizards", "wizards");
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String teamID);
    }

    public interface TeamResponseListener {
        void onError(String message);

        void onResponse(String teamInfo, String teamNickName, String teamFullName, String teamID);
    }

    public interface GameResponseListener {
        void onError(String message);

        void onResponse(String[] awayTeams, String[] homeTeams, String[][] awayScoreLines, String[][] homeScoreLines, String[] awayFinalScores, String[] homeFinalScores, int[] totalQuarters, String[] gameClock, boolean[] halftimeStatus, boolean[] gamePeriodStatus, int[] gamePeriods, String[] awayTeamAwayRecord, String[] awayTeamHomeRecord, String[] awayTeamTotalRecord, String[] homeTeamAwayRecord, String[] homeTeamHomeRecord, String[] homeTeamTotalRecord);
    }

    public interface StandingsResponseListener {
        void onError(String message);
        void onResponse(Map<String, String> standings);
    }

    public interface TeamStandingsResponseListener {
        void onError(String message);
        void onResponse(Map<String, String[]> standings);
    }

    public interface PlayersGamesResponseListener {
        void onError(String message);

        void onResponse(String[] playerTeamId, String[] homeTeams, String[] awayTeams, int[] homeTeamScores, int[] awayTeanScores, String[] gameDates, String[] mins, String[] pts, String[] reb, String[] ast, String[] stl, String[] blk, String[] pf, String[] to, String[] fg_pct, String[] fg3_pct, String[] ft_pct);
    }

    public interface PlayersGameIdsResponseListener {
        void onError(String message);
        void onResponse(ArrayList<Integer> gameIds);
    }

    public interface PlayerResponseListener {
        void onError(String message);

        void onResponse(String playerName, int playerId, String playerTeamName);
    }

    public interface PlayerStatsResponseListener {
        void onError(String message);

        void onResponse(String playerName, String displayMessage);
    }

    public interface PlayerImageResponseListener {
        void onError(String message);

        void onResponse(String playerImageID);
    }

    public interface PlayerListResponseListener {
        void onError(String message);

        void onResponse(ArrayList<String> playerList);
    }

    public void getTeamID(String teamName, VolleyResponseListener volleyResponseListener) {
        String url = "";
        if (teamName.trim().length()==3) {
            url = QUERY_TEAM_CODE + teamName;
        } else {
            url = QUERY_FULL_NAME + teamName;
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray teamArray = response.getJSONArray("response");
                    if (teamName.trim().equals("")) {
                        volleyResponseListener.onResponse("no input");
                    } else if (teamArray.length() == 0) {
                        volleyResponseListener.onResponse("");
                    } else {
                        JSONObject teamInfo = teamArray.getJSONObject(0);
                        teamID = teamInfo.getString("id");
                        volleyResponseListener.onResponse(teamID);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> volleyResponseListener.onError("Error")) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("content-type", "application/json");
                params.put("x-rapidapi-key", "212d734f82msh63a8f35631feeadp11bb0ejsn3c5f43f66a62");
                params.put("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");
                return params;
            }
        };
        NBASingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getTeam(String teamName, TeamResponseListener teamResponseListener) {
        getTeamID(teamName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                teamResponseListener.onError(message);
            }

            @Override
            public void onResponse(String teamID) {
                if (teamID.equals("no input")) {
                    teamResponseListener.onResponse("no input", "no input", "no input", "no input");
                } else if (teamID.equals("")) {
                    teamResponseListener.onResponse("", "", "", "");
                } else {
                    String url = QUERY_TEAM_STANDINGS + teamID;
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray teamArray = response.getJSONArray("response");
                                JSONObject allInfo = teamArray.getJSONObject(0);
                                JSONObject teamName = allInfo.getJSONObject("team");
                                JSONObject teamConference = allInfo.getJSONObject("conference");
                                JSONObject teamWins = allInfo.getJSONObject("win");
                                JSONObject teamLosses = allInfo.getJSONObject("loss");
                                teamFullName = teamName.getString("name");
                                teamNickName = teamName.getString("nickname");
                                teamInfo = teamName.getString("name") +  "\n" + teamConference.getString("name").substring(0,1).toUpperCase() + teamConference.getString("name").substring(1) + "ern Conference #" + teamConference.getString("rank") + "\nRecord: (" + teamWins.getString("total") + "-" + teamLosses.getString("total") + ")\nL-10: (" + teamWins.getString("lastTen") + "-" + teamLosses.getString("lastTen") + ")";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            teamResponseListener.onResponse(teamInfo, teamNickName, teamFullName, teamID);
                        }
                    }, error -> teamResponseListener.onError("Error")) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("content-type", "application/json");
                            params.put("x-rapidapi-key", "212d734f82msh63a8f35631feeadp11bb0ejsn3c5f43f66a62");
                            params.put("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");
                            return params;
                        }
                    };
                    NBASingleton.getInstance(context).addToRequestQueue(request);
                }
            }
        });
    }

    public void getGames(String teamID, GameResponseListener gameResponseListener) {

        getStandings(new StandingsResponseListener() {
            @Override
            public void onError(String message) {
                gameResponseListener.onError("Couldn't load standings");
            }

            @Override
            public void onResponse(Map<String, String> standings) {
                String url = QUERY_GAMES + teamID;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray gamesArray = response.getJSONArray("response");
                            int count = 0;
                            for (int i = gamesArray.length()-1; count!= 10; i--) {
                                JSONObject gameInfo = gamesArray.getJSONObject(i);
                                JSONObject gameStatus = gameInfo.getJSONObject("status");
                                if (!gameStatus.getString("long").equalsIgnoreCase("Scheduled")) {
                                    JSONObject teams = gameInfo.getJSONObject("teams");
                                    JSONObject visitorNames = teams.getJSONObject("visitors");
                                    JSONObject homeNames = teams.getJSONObject("home");
                                    awayTeams[count] = visitorNames.getString("nickname");
                                    homeTeams[count] = homeNames.getString("nickname");
                                    awayTeamAwayRecord[count] = standings.get(visitorNames.getString("code") + "_awayRecord");
                                    awayTeamHomeRecord[count] = standings.get(visitorNames.getString("code") + "_homeRecord");
                                    awayTeamTotalRecord[count] = standings.get(visitorNames.getString("code") + "_totalRecord");
                                    homeTeamAwayRecord[count] = standings.get(homeNames.getString("code") + "_awayRecord");
                                    homeTeamHomeRecord[count] = standings.get(homeNames.getString("code") + "_homeRecord");
                                    homeTeamTotalRecord[count] = standings.get(homeNames.getString("code") + "_totalRecord");
                                    JSONObject periods = gameInfo.getJSONObject("periods");
                                    JSONObject scores = gameInfo.getJSONObject("scores");
                                    JSONObject visitorScores = scores.getJSONObject("visitors");
                                    JSONArray visitorLineScore = visitorScores.getJSONArray("linescore");
                                    for (int j = 0; j < visitorLineScore.length(); j++) {
                                        awayScoreLines[count][j] = visitorLineScore.get(j).toString();
                                    }
                                    awayFinalScores[count] = visitorScores.get("points").toString();
                                    JSONObject homeScores = scores.getJSONObject("home");
                                    JSONArray homeLineScore = homeScores.getJSONArray("linescore");
                                    for (int j = 0; j < homeLineScore.length(); j++) {
                                        homeScoreLines[count][j] = homeLineScore.get(j).toString();
                                    }
                                    homeFinalScores[count] = homeScores.get("points").toString();
                                    totalQuarters[count] = visitorLineScore.length();
                                    if (gameStatus.getString("long").equalsIgnoreCase("In Play")) {
                                        gameClock[count] = gameStatus.get("clock").toString();
                                        halftimeStatus[count] = gameStatus.getBoolean("halftime");
                                        gamePeriodStatus[count] = periods.getBoolean("endOfPeriod");
                                        gamePeriods[count] = periods.getInt("current");
                                    } else {
                                        gameClock[count] = "";
                                        gamePeriods[count] = 0;
                                        gamePeriodStatus[count] = true;
                                        halftimeStatus[count] = false;
                                    }
                                    count++;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        gameResponseListener.onResponse(awayTeams, homeTeams, awayScoreLines, homeScoreLines, awayFinalScores, homeFinalScores, totalQuarters, gameClock, halftimeStatus, gamePeriodStatus, gamePeriods, awayTeamAwayRecord, awayTeamHomeRecord, awayTeamTotalRecord, homeTeamAwayRecord, homeTeamHomeRecord, homeTeamTotalRecord);
                    }
                }, error -> gameResponseListener.onError("Error")) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("content-type", "application/json");
                        params.put("x-rapidapi-key", "212d734f82msh63a8f35631feeadp11bb0ejsn3c5f43f66a62");
                        params.put("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");
                        return params;
                    }
                };
                NBASingleton.getInstance(context).addToRequestQueue(request);
            }
        });
    }

    public void getStandings(StandingsResponseListener standingsResponseListener) {
        String url = QUERY_STANDINGS;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray standingsArray = response.getJSONArray("response");
                    for (int i = 0; i < standingsArray.length(); i++) {
                        JSONObject standingsObject = standingsArray.getJSONObject(i);
                        JSONObject teamStandingsObject = standingsObject.getJSONObject("team");
                        JSONObject teamWins = standingsObject.getJSONObject("win");
                        JSONObject teamLosses = standingsObject.getJSONObject("loss");
                        standings.put(teamStandingsObject.getString("code") + "_homeRecord", teamWins.getInt("home") + "-" + teamLosses.getInt("home"));
                        standings.put(teamStandingsObject.getString("code") + "_awayRecord", teamWins.getInt("away") + "-" + teamLosses.getInt("away"));
                        standings.put(teamStandingsObject.getString("code") + "_totalRecord", teamWins.getInt("total") + "-" + teamLosses.getInt("total"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                standingsResponseListener.onResponse(standings);
            }
        }, error -> standingsResponseListener.onError("Could not retrieve standings")) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("content-type", "application/json");
                params.put("x-rapidapi-key", "212d734f82msh63a8f35631feeadp11bb0ejsn3c5f43f66a62");
                params.put("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");
                return params;
            }
        };
        NBASingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getTeamStandings(TeamStandingsResponseListener teamStandingsResponseListener) {
        String url = QUERY_STANDINGS;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray standingsArray = response.getJSONArray("response");
                    for (int i = 0; i < standingsArray.length(); i++) {
                        JSONObject standingsObject = standingsArray.getJSONObject(i);
                        JSONObject teamStandingsObject = standingsObject.getJSONObject("team");
                        JSONObject conference = standingsObject.getJSONObject("conference");
                        teamStandings.put(conference.getString("name") + "_" + conference.get("rank"), new String[]{conference.get("rank").toString(), teamStandingsObject.getString("name"), conference.get("win").toString(), conference.get("loss").toString(), teamStandingsObject.getString("nickname")});
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                teamStandingsResponseListener.onResponse(teamStandings);
            }
        }, error -> teamStandingsResponseListener.onError("Could not retrieve standings")) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("content-type", "application/json");
                params.put("x-rapidapi-key", "212d734f82msh63a8f35631feeadp11bb0ejsn3c5f43f66a62");
                params.put("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");
                return params;
            }
        };
        NBASingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getPlayersGameStats(String playerName, PlayersGamesResponseListener playersGamesResponseListener) {
        getPlayerID(playerName, new PlayerResponseListener() {
            @Override
            public void onError(String message) {
                playersGamesResponseListener.onError(message);
            }
            @Override
            public void onResponse(String playerName, int playerId, String playerTeamName) {
                getPlayerGameIDs(playerName, playerId, new PlayersGameIdsResponseListener() {
                    @Override
                    public void onError(String message) {
                        playersGamesResponseListener.onError("Couldn't get url for games");
                    }
                    @Override
                    public void onResponse(ArrayList<Integer> gameIds) {

                        Collections.sort(gameIds, Collections.reverseOrder());
                        String url = PLAYER_STATS + playerId;
                        for (int i = 0; i < 9; i++) {
                            url += "&game_ids[]=" + gameIds.get(i);
                        }
                        url += "&game_ids[]=" + gameIds.get(9);
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray playerStatsArray = response.getJSONArray("data");
                                    for (int i = 0; i < playerStatsArray.length(); i++) {
                                        JSONObject playerStatsObject = playerStatsArray.getJSONObject(i);
                                        JSONObject playerIDObject = playerStatsObject.getJSONObject("player");
                                        JSONObject gameObject = playerStatsObject.getJSONObject("game");
                                        playerTeamId[i] = "" + playerIDObject.get("team_id");
                                        homeTeams[i] = "" + gameObject.get("home_team_id");
                                        awayTeams[i] = "" + gameObject.get("visitor_team_id");
                                        homeTeamScore[i] = gameObject.getInt("home_team_score");
                                        awayTeamScore[i] = gameObject.getInt("visitor_team_score");
                                        gameDate[i] = gameObject.getString("date");
                                        min[i] = playerStatsObject.get("min").toString();
                                        pts[i] = playerStatsObject.get("pts").toString();
                                        reb[i] = playerStatsObject.get("reb").toString();
                                        ast[i] = playerStatsObject.get("ast").toString();
                                        stl[i] = playerStatsObject.get("stl").toString();
                                        blk[i] = playerStatsObject.get("blk").toString();
                                        pf[i] = playerStatsObject.get("pf").toString();
                                        to[i] = playerStatsObject.get("turnover").toString();
                                        if (playerStatsObject.get("fg_pct").toString().equalsIgnoreCase("null")) {
                                            fg_pct[i] = "0.0";
                                        } else {
                                            fg_pct[i] = "" + (playerStatsObject.getDouble("fg_pct") * 100);
                                        }
                                        if (playerStatsObject.get("fg3_pct").toString().equalsIgnoreCase("null")){
                                            fg3_pct[i] = "0.0";
                                        } else {
                                            fg3_pct[i] = "" + (playerStatsObject.getDouble("fg3_pct") * 100);
                                        }
                                        if (playerStatsObject.get("ft_pct").toString().equalsIgnoreCase("null")) {
                                            ft_pct[i] = "0.0";
                                        } else {
                                            ft_pct[i] = "" + (playerStatsObject.getDouble("ft_pct") * 100);
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                playersGamesResponseListener.onResponse(playerTeamId, homeTeams, awayTeams, homeTeamScore, awayTeamScore, gameDate, min, pts, reb, ast, stl, blk, pf, to, fg_pct, fg3_pct, ft_pct);
                            }
                        }, error -> playersGamesResponseListener.onError("Couldn't get games"));
                        NBASingleton.getInstance(context).addToRequestQueue(request);
                    }
                });
            }
        });
    }

    public void getPlayerGameIDs (String playerName, int playerId, PlayersGameIdsResponseListener playersGameIdsResponseListener) {
            gameIds = new ArrayList<>();
            String url = PLAYER_STATS + playerId;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray playerStatsArray = response.getJSONArray("data");
                        for (int i = 0; i < playerStatsArray.length(); i++) {
                            JSONObject playerStatsObject = playerStatsArray.getJSONObject(i);
                            JSONObject playerIDObject = playerStatsObject.getJSONObject("player");
                            JSONObject gameObject = playerStatsObject.getJSONObject("game");
                            if (!playerStatsObject.get("min").equals("00") && !playerStatsObject.get("min").equals("") && gameObject.get("status").equals("Final") && playerId == playerIDObject.getInt("id")) {
                                gameIds.add(gameObject.getInt("id"));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    playersGameIdsResponseListener.onResponse(gameIds);
                }
            }, error -> playersGameIdsResponseListener.onError("Couldn't retrieve games"));
            NBASingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getPlayerID(String playerName, PlayerResponseListener playerResponseListener) {
        String url = QUERY_PLAYER_INFO;
        if (playerName.contains(".")) {
            playerName = playerName.replace(".", "");
            url += playerName;
        } else {
            url += playerName;
        }
        String finalPlayerName = playerName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray playerArray = response.getJSONArray("data");
                    if (playerArray.length() == 0) {
                        playerResponseListener.onResponse("gibberish", 0, "gibberish");
                    } else if (finalPlayerName.trim().equals("")) {
                        playerResponseListener.onResponse("", 0, "");
                    } else if (playerArray.length() > 1){
                        playerResponseListener.onResponse("more than 1", 0, "more than 1");
                    } else {
                        JSONObject playerInfo = playerArray.getJSONObject(0);
                        JSONObject playerTeamInfo = playerInfo.getJSONObject("team");
                        playerID = playerInfo.getInt("id");
                        playerFullName = playerInfo.getString("first_name") + " " + playerInfo.getString("last_name");
                        playerTeam = playerTeamInfo.getString("full_name");
                        playerResponseListener.onResponse(playerFullName, playerID, playerTeam);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> playerResponseListener.onError("Please enter a player's name"));
        NBASingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getPlayerStats(String playerName, PlayerStatsResponseListener playerStatsResponseListener) {
        p1_points = 0;
        p1_rebounds = 0;
        p1_assists = 0;
        p1_steals = 0;
        p1_blocks = 0;

        p1_ppg = 0;
        p1_rpg = 0;
        p1_apg = 0;
        p1_spg = 0;
        p1_bpg = 0;
        p1_games = 0;
        getPlayerID(playerName, new PlayerResponseListener() {
            @Override
            public void onError(String message) {
                playerStatsResponseListener.onError(message);
            }

            @Override
            public void onResponse(String playerName, int playerId, String playerTeamName) {
                if (playerName.equals("gibberish")) {
                    playerStatsResponseListener.onResponse("gibberish", "gibberish");
                } else if (playerName.equals("")) {
                    playerStatsResponseListener.onResponse("", "");
                } else if (playerName.equals("more than 1")) {
                    playerStatsResponseListener.onResponse("more than 1", "");
                } else {
                    String url = PLAYER_STATS + playerId;
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray playerStatsArray = response.getJSONArray("data");

                                for (int i = 0; i < playerStatsArray.length(); i++) {
                                    JSONObject playerStatsObject = playerStatsArray.getJSONObject(i);
                                    JSONObject playerIDObject = playerStatsObject.getJSONObject("player");
                                    JSONObject gameObject = playerStatsObject.getJSONObject("game");
                                    if (!playerStatsObject.get("min").equals("00") && !playerStatsObject.get("min").equals("") && gameObject.get("status").equals("Final") && playerId == playerIDObject.getInt("id")) {
                                        p1_games++;
                                        p1_points += playerStatsObject.getDouble("pts");
                                        p1_rebounds += playerStatsObject.getDouble("reb");
                                        p1_assists += playerStatsObject.getDouble("ast");
                                        p1_steals += playerStatsObject.getDouble("stl");
                                        p1_blocks += playerStatsObject.getDouble("blk");
                                    }
                                    if (i == playerStatsArray.length()-1) {
                                        if (p1_games == 0) {
                                            String result = playerName + "\n" + playerTeamName + "\n";
                                            result += "Season Stat Averages\n";
                                            result += p1_games + " GP ";
                                            result += p1_games + " PTS ";
                                            result += p1_games + " REB ";
                                            result += p1_games + " AST";
                                            playerStatsResponseListener.onResponse(playerName, result);
                                        } else {
                                            p1_ppg = p1_points/p1_games;
                                            p1_rpg = p1_rebounds/p1_games;
                                            p1_apg = p1_assists/p1_games;
                                            p1_spg = p1_steals/p1_games;
                                            p1_bpg = p1_blocks/p1_games;

                                            String result = playerName + "\n" + playerTeamName + "\n";
                                            result += "Season Stat Averages\n";
                                            result += p1_games + " GP ";
                                            result += df.format(p1_ppg) + " PTS ";
                                            result += df.format(p1_rpg) + " REB ";
                                            result += df.format(p1_apg) + " AST";

                                            playerStatsResponseListener.onResponse(playerName, result);
                                        }
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, error -> playerStatsResponseListener.onError("Error"));
                    NBASingleton.getInstance(context).addToRequestQueue(request);
                }
            }
        });
    }

    public void getPlayerImage(String playerName, PlayerImageResponseListener playerImageResponseListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, PLAYER_IMAGE_ID, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject initialObject = response.getJSONObject("league");
                    JSONArray standardInfo = initialObject.getJSONArray("standard");
                    for (int i = 0; i < standardInfo.length(); i++) {
                        JSONObject playerInfo = standardInfo.getJSONObject(i);
                        String playerInfoName = playerInfo.get("firstName") + " " + playerInfo.get("lastName");
                        if (playerInfoName.contains(".")) {
                            if (playerName.equalsIgnoreCase(playerInfoName.replace(".",""))) {
                                playerImageID = playerInfo.get("personId").toString();
                                playerImageResponseListener.onResponse(playerImageID);
                                break;
                            }
                        }
                        else if (playerName.equalsIgnoreCase(playerInfoName)) {
                            playerImageID = playerInfo.get("personId").toString();
                            playerImageResponseListener.onResponse(playerImageID);
                            break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> playerImageResponseListener.onError("Failed to retrieve playerId"));
        NBASingleton.getInstance(context).addToRequestQueue(request);
    }

    public void getAllPlayers(PlayerListResponseListener playerListResponseListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, PLAYER_IMAGE_ID, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject initialObject = response.getJSONObject("league");
                    JSONArray standardInfo = initialObject.getJSONArray("standard");
                    for (int i = 0; i < standardInfo.length(); i++) {
                        JSONObject playerInfo = standardInfo.getJSONObject(i);
                        String playerInfoName = playerInfo.get("firstName") + " " + playerInfo.get("lastName");
                        playerList.add(playerInfoName);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                playerListResponseListener.onResponse(playerList);
            }
        }, error -> playerListResponseListener.onError("Failed to retrieve players"));
        NBASingleton.getInstance(context).addToRequestQueue(request);
    }
}
