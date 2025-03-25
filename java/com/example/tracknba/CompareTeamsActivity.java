package com.example.tracknba;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class CompareTeamsActivity extends AppCompatActivity implements View.OnClickListener{
    protected TextView resultsTextView;
    protected TextView resultsTextView2;
    protected AutoCompleteTextView teamEt;
    protected AutoCompleteTextView teamEt2;
    protected Button btnFetch;
    protected CustomDialogBar customDialogBar;
    ImageView imageView;
    ImageView imageView2;
    final NBAService nbaService = new NBAService(CompareTeamsActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_team);
        resultsTextView = findViewById(R.id.results);
        resultsTextView2 = findViewById(R.id.results_2);
        teamEt = findViewById(R.id.team_et);
        teamEt2 = findViewById(R.id.team_et_2);
        btnFetch = findViewById(R.id.btn_fet);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

        btnFetch.setOnClickListener(this);

        String[] listOfTeams = new String[30];
        listOfTeams[0] = "Atlanta Hawks";
        listOfTeams[1] = "Boston Celtics";
        listOfTeams[2] = "Brooklyn Nets";
        listOfTeams[3] = "Charlotte Hornets";
        listOfTeams[4] = "Chicago Bulls";
        listOfTeams[5] = "Cleveland Cavaliers";
        listOfTeams[6] = "Dallas Mavericks";
        listOfTeams[7] = "Denver Nuggets";
        listOfTeams[8] = "Detroit Pistons";
        listOfTeams[9] = "Golden State Warriors";
        listOfTeams[10] = "Houston Rockets";
        listOfTeams[11] = "Indiana Pacers";
        listOfTeams[12] = "LA Clippers";
        listOfTeams[13] = "Los Angeles Lakers";
        listOfTeams[14] = "Memphis Grizzlies";
        listOfTeams[15] = "Miami Heat";
        listOfTeams[16] = "Milwaukee Bucks";
        listOfTeams[17] = "Minnesota Timberwolves";
        listOfTeams[18] = "New Orleans Pelicans";
        listOfTeams[19] = "New York Knicks";
        listOfTeams[20] = "Oklahoma City Thunder";
        listOfTeams[21] = "Orlando Magic";
        listOfTeams[22] = "Philadelphia 76ers";
        listOfTeams[23] = "Phoenix Suns";
        listOfTeams[24] = "Portland Trailblazers";
        listOfTeams[25] = "Sacramento Kings";
        listOfTeams[26] = "San Antonio Spurs";
        listOfTeams[27] = "Toronto Raptors";
        listOfTeams[28] = "Utah Jazz";
        listOfTeams[29] = "Washington Wizards";

        ArrayAdapter<String> adapter = new ArrayAdapter<>(CompareTeamsActivity.this, android.R.layout.simple_list_item_1, listOfTeams);
        teamEt.setAdapter(adapter);
        teamEt2.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        customDialogBar = new CustomDialogBar(CompareTeamsActivity.this);
        customDialogBar.ShowDialog("Comparing Teams");
        if (v.getId() == R.id.btn_fet) {
            if (teamEt.getText().toString().equalsIgnoreCase(teamEt2.getText().toString())) {
                customDialogBar.HideDialog();
                Toast.makeText(this, "Please enter 2 different teams", Toast.LENGTH_SHORT).show();
            } else {
                nbaService.getTeam(teamEt.getText().toString(), new NBAService.TeamResponseListener() {
                    @Override
                    public void onError(String message) {
                        customDialogBar.HideDialog();
                        Toast.makeText(CompareTeamsActivity.this, "Could not find first team", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String teamInfo, String teamNickName, String teamFullName, String teamID) {
                        if (teamInfo.equals("no input")) {
                            customDialogBar.HideDialog();
                            resultsTextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(CompareTeamsActivity.this, "Please enter first team's name", Toast.LENGTH_SHORT).show();
                        } else if (teamInfo.equals("")) {
                            customDialogBar.HideDialog();
                            resultsTextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(CompareTeamsActivity.this, "Check spelling of first team's name", Toast.LENGTH_SHORT).show();
                        } else {
                            customDialogBar.HideDialog();
                            Context context = imageView.getContext();
                            int imageID = context.getResources().getIdentifier("_"+teamNickName.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                            imageView.setImageResource(imageID);
                            resultsTextView.setText(teamInfo);
                        }
                    }
                });

                nbaService.getTeam(teamEt2.getText().toString(), new NBAService.TeamResponseListener() {
                    @Override
                    public void onError(String message) {
                        customDialogBar.HideDialog();
                        Toast.makeText(CompareTeamsActivity.this, "Could not find second team", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String teamInfo, String teamNickName, String teamFullName, String teamID) {
                        if (teamInfo.equals("no input")) {
                            customDialogBar.HideDialog();
                            resultsTextView2.setText("");
                            imageView2.setImageBitmap(null);
                            Toast.makeText(CompareTeamsActivity.this, "Please enter second team's name", Toast.LENGTH_SHORT).show();
                        } else if (teamInfo.equals("")) {
                            customDialogBar.HideDialog();
                            resultsTextView2.setText("");
                            imageView2.setImageBitmap(null);
                            Toast.makeText(CompareTeamsActivity.this, "Check spelling of second team's name", Toast.LENGTH_SHORT).show();
                        } else {
                            customDialogBar.HideDialog();
                            Context context = imageView.getContext();
                            int imageID = context.getResources().getIdentifier("_"+teamNickName.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                            imageView2.setImageResource(imageID);
                            resultsTextView2.setText(teamInfo);
                        }
                    }
                });
            }
        }
    }
}
