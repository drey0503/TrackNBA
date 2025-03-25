package com.example.tracknba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknba.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ComparePlayersActivity extends AppCompatActivity implements View.OnClickListener {
    protected int id1, id2, p1_games, p2_games;

    protected double p1_points, p1_rebounds, p1_assists, p1_steals, p1_blocks;
    protected double p1_ppg, p1_rpg, p1_apg, p1_spg, p1_bpg;

    protected double p2_points, p2_rebounds, p2_assists, p2_steals, p2_blocks;
    protected double p2_ppg, p2_rpg, p2_apg, p2_spg, p2_bpg;

    protected String myUrl;
    protected TextView p1_game1TextView, random, p1_pts, p1rbs, p1ast, p1bl, p1st;
    protected TextView p2_game1TextView, p2g, p2pts, p2rbs, p2ast, p2bl, p2st;

    protected AutoCompleteTextView p1_EditText;
    protected AutoCompleteTextView p2_EditText;

    protected CustomDialogBar customDialogBar;
    protected Button btnCompare;
    protected String player1_name, player2_name;
    private static final DecimalFormat df = new DecimalFormat("0.0");
    final NBAService nbaService = new NBAService(ComparePlayersActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compareplayers);

        p1_EditText = findViewById(R.id.player_1_et);
        p2_EditText = findViewById(R.id.player_2_et);

        random = findViewById(R.id.random);
        p1_pts = findViewById(R.id.p1_ppg);
        p1rbs = findViewById(R.id.p1_rpg);
        p1ast = findViewById(R.id.p1_apg);
        p1bl = findViewById(R.id.p1_bpg);
        p1st = findViewById(R.id.p1_spg);

        p2g = findViewById(R.id.p2_games);
        p2pts = findViewById(R.id.p2_ppg);
        p2rbs = findViewById(R.id.p2_rpg);
        p2ast = findViewById(R.id.p2_apg);
        p2bl = findViewById(R.id.p2_bpg);
        p2st = findViewById(R.id.p2_spg);

        btnCompare = findViewById(R.id.btn_compare);

        btnCompare.setOnClickListener(this);

        nbaService.getAllPlayers(new NBAService.PlayerListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(ComparePlayersActivity.this, "Could not retrieve list of players", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(ArrayList<String> playerList) {
                String[] listOfPlayer = new String[playerList.size()];
                listOfPlayer = playerList.toArray(listOfPlayer);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ComparePlayersActivity.this, android.R.layout.simple_list_item_1, listOfPlayer);
                p1_EditText.setAdapter(adapter);
                p2_EditText.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_compare) {
            // player1_name: Stores user input of player 1's full name
            player1_name = p1_EditText.getText().toString();

            // player2_name: Stores user input of player 2's full name
            player2_name = p2_EditText.getText().toString();
            if (player1_name.equalsIgnoreCase(player2_name)) {
                Toast.makeText(this, "Please enter 2 different players", Toast.LENGTH_SHORT).show();
            } else {
                id1 = 0;
                id2 = 0;

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

                p2_points = 0;
                p2_rebounds = 0;
                p2_assists = 0;
                p2_steals = 0;
                p2_blocks = 0;

                p2_ppg = 0;
                p2_rpg = 0;
                p2_apg = 0;
                p2_spg = 0;
                p2_bpg = 0;

                p1_games = 0;
                p2_games = 0;

                customDialogBar = new CustomDialogBar(ComparePlayersActivity.this);
                customDialogBar.ShowDialog("Comparing Players");

                // Call searchPlayer() to search for player and set the information to be displayed
                searchPlayer(player1_name, id1, id2);
            }
        }
    }
    public void searchPlayer(String player_name, int player1_id, int player2_id) {
        myUrl = "https://www.balldontlie.io/api/v1/players?search=" + player_name;
        Uri uri = Uri.parse(myUrl).buildUpon().build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new ComparePlayersActivity.AsyncTasks().execute(url);
    }

    class AsyncTasks extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls [0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest2(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                parseSearch(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void parseSearch(String data) throws JSONException {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray playerArray = jsonObject.getJSONArray("data");
            if (playerArray.length() == 0) {
                customDialogBar.HideDialog();
                Toast.makeText(ComparePlayersActivity.this, "Check Spelling of Player's Names", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < playerArray.length(); i++) {
                    JSONObject playerObject = playerArray.getJSONObject(i);
                    String playerFirstName = playerObject.get("first_name").toString();
                    if (player1_name.toLowerCase().contains(playerFirstName.toLowerCase())) {
                        id1 = playerObject.getInt("id");
                        searchPlayer(player2_name, id1, id2);
                    } else if (player2_name.toLowerCase().contains(playerFirstName.toLowerCase()) && id1 != 0){
                        id2 = playerObject.getInt("id");
                        getStats(id1, id2, 1);
                    } else {
                        customDialogBar.HideDialog();
                        p1_game1TextView.setText("");
                        p2_game1TextView.setText("");
                        Toast.makeText(ComparePlayersActivity.this, "Player not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public void getStats(int first_playerID, int second_playerID, int page) {
        myUrl = "https://www.balldontlie.io/api/v1/stats?seasons[]=2022&player_ids[]=" + first_playerID + "&player_ids[]=" + second_playerID + "&page=" + page;
        Uri uri = Uri.parse(myUrl).buildUpon().build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new ComparePlayersActivity.AsyncTasks2().execute(url);
    }

    class AsyncTasks2 extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls [0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest2(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                parseStats(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public void parseStats(String data) throws JSONException {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray playerStatsArray = jsonObject.getJSONArray("data");
            JSONObject metaDataObject = jsonObject.getJSONObject("meta");
            int current_page = metaDataObject.getInt("current_page");
            int total_pages = metaDataObject.getInt("total_pages");

            if (current_page < total_pages) {
                int next_page = metaDataObject.getInt("next_page");
                for (int i = 0; i < playerStatsArray.length(); i++) {
                    JSONObject playerStatsObject = playerStatsArray.getJSONObject(i);
                    JSONObject playerIDObject = playerStatsObject.getJSONObject("player");
                    JSONObject gameObject = playerStatsObject.getJSONObject("game");
                    if (!playerStatsObject.get("min").equals("00") && !playerStatsObject.get("min").equals("") && gameObject.get("status").equals("Final") && id1 == playerIDObject.getInt("id")) {
                        p1_games++;
                        p1_points += playerStatsObject.getDouble("pts");
                        p1_rebounds += playerStatsObject.getDouble("reb");
                        p1_assists += playerStatsObject.getDouble("ast");
                        p1_steals += playerStatsObject.getDouble("stl");
                        p1_blocks += playerStatsObject.getDouble("blk");
                    } else if (!playerStatsObject.get("min").equals("00") && !playerStatsObject.get("min").equals("") && gameObject.get("status").equals("Final") && id2 == playerIDObject.getInt("id")) {
                        p2_games++;
                        p2_points += playerStatsObject.getDouble("pts");
                        p2_rebounds += playerStatsObject.getDouble("reb");
                        p2_assists += playerStatsObject.getDouble("ast");
                        p2_steals += playerStatsObject.getDouble("stl");
                        p2_blocks += playerStatsObject.getDouble("blk");
                    }
                }
                current_page = next_page;
                getStats(id1, id2, current_page);
            } else {
                for (int i = 0; i < playerStatsArray.length(); i++) {
                    JSONObject playerStatsObject = playerStatsArray.getJSONObject(i);
                    JSONObject playerIDObject = playerStatsObject.getJSONObject("player");
                    JSONObject gameObject = playerStatsObject.getJSONObject("game");
                    if (!playerStatsObject.get("min").equals("00") && !playerStatsObject.get("min").equals("") && gameObject.get("status").equals("Final") && id1 == playerIDObject.getInt("id")) {
                        p1_games++;
                        p1_points += playerStatsObject.getDouble("pts");
                        p1_rebounds += playerStatsObject.getDouble("reb");
                        p1_assists += playerStatsObject.getDouble("ast");
                        p1_steals += playerStatsObject.getDouble("stl");
                        p1_blocks += playerStatsObject.getDouble("blk");
                    } else if (!playerStatsObject.get("min").equals("00") && !playerStatsObject.get("min").equals("") && gameObject.get("status").equals("Final") && id2 == playerIDObject.getInt("id")) {
                        p2_games++;
                        p2_points += playerStatsObject.getDouble("pts");
                        p2_rebounds += playerStatsObject.getDouble("reb");
                        p2_assists += playerStatsObject.getDouble("ast");
                        p2_steals += playerStatsObject.getDouble("stl");
                        p2_blocks += playerStatsObject.getDouble("blk");
                    }

                    if (p1_games != 0) {
                        p1_ppg = p1_points/p1_games;
                        p1_rpg = p1_rebounds/p1_games;
                        p1_apg = p1_assists/p1_games;
                        p1_spg = p1_steals/p1_games;
                        p1_bpg = p1_blocks/p1_games;
                    } else {
                        p1_ppg = 0;
                        p1_rpg = 0;
                        p1_apg = 0;
                        p1_spg = 0;
                        p1_bpg = 0;
                    }

                    if (p2_games != 0) {
                        p2_ppg = p2_points/p2_games;
                        p2_rpg = p2_rebounds/p2_games;
                        p2_apg = p2_assists/p2_games;
                        p2_spg = p2_steals/p2_games;
                        p2_bpg = p2_blocks/p2_games;
                    } else {
                        p2_ppg = 0;
                        p2_rpg = 0;
                        p2_apg = 0;
                        p2_spg = 0;
                        p2_bpg = 0;
                    }

                    double p1pt = Double.parseDouble(df.format(p1_ppg));
                    double p1rb = Double.parseDouble(df.format(p1_rpg));
                    double p1as = Double.parseDouble(df.format(p1_apg));
                    double p1s = Double.parseDouble(df.format(p1_spg));
                    double p1b = Double.parseDouble(df.format(p1_bpg));

                    double p2pt = Double.parseDouble(df.format(p2_ppg));
                    double p2rb = Double.parseDouble(df.format(p2_rpg));
                    double p2as = Double.parseDouble(df.format(p2_apg));
                    double p2s = Double.parseDouble(df.format(p2_spg));
                    double p2b = Double.parseDouble(df.format(p2_bpg));

                    String test = String.valueOf(p1_games);
                    String p2ga = String.valueOf(p2_games);

                    String p1ppg = String.valueOf(p1pt);
                    String p1rpg = String.valueOf(p1rb);
                    String p1apg = String.valueOf(p1as);
                    String p1spg = String.valueOf(p1s);
                    String p1bpg = String.valueOf(p1b);

                    String p2ppg = String.valueOf(p2pt);
                    String p2rpg = String.valueOf(p2rb);
                    String p2apg = String.valueOf(p2as);
                    String p2spg = String.valueOf(p2s);
                    String p2bpg = String.valueOf(p2b);


                    customDialogBar.HideDialog();

                    random.setText(test);
                    p1_pts.setText(p1ppg);
                    p1rbs.setText(p1rpg);
                    p1ast.setText(p1apg);
                    p1bl.setText(p1bpg);
                    p1st.setText(p1spg);

                    p2g.setText(p2ga);
                    p2pts.setText(p2ppg);
                    p2rbs.setText(p2rpg);
                    p2ast.setText(p2apg);
                    p2bl.setText(p2bpg);
                    p2st.setText(p2spg);
                }
            }
        }
    }
}
