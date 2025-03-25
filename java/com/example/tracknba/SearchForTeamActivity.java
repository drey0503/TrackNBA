package com.example.tracknba;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SearchForTeamActivity extends AppCompatActivity {
    TextView resultsTextView;
    AutoCompleteTextView teamEt;
    Button btnFetch;
    Button addButn;
    boolean search = false;
    String teamName;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    ImageView imageView;
    protected CustomDialogBar customDialogBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_team);
        resultsTextView = findViewById(R.id.results);
        teamEt = findViewById(R.id.team_et);
        btnFetch = findViewById(R.id.btn_fet);
        addButn = findViewById(R.id.addBtn);
        imageView = findViewById(R.id.imageView);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        final NBAService nbaService = new NBAService(SearchForTeamActivity.this);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchForTeamActivity.this, android.R.layout.simple_list_item_1, listOfTeams);
        teamEt.setAdapter(adapter);

        teamEt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customDialogBar = new CustomDialogBar(SearchForTeamActivity.this);
                customDialogBar.ShowDialog("Fetching Team Information");
                search = false;
                nbaService.getTeam(teamEt.getText().toString(), new NBAService.TeamResponseListener() {
                    @Override
                    public void onError(String message) {
                        customDialogBar.HideDialog();
                        search = false;
                        resultsTextView.setText("");
                        imageView.setImageBitmap(null);
                        Toast.makeText(SearchForTeamActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String teamInfo, String teamNickName, String teamFullName, String teamID) {
                        if (teamInfo.equals("no input")) {
                            customDialogBar.HideDialog();
                            search = false;
                            resultsTextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForTeamActivity.this, "Please enter a team's name", Toast.LENGTH_SHORT).show();
                        } else if (teamInfo.equals("")) {
                            customDialogBar.HideDialog();
                            search = false;
                            resultsTextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForTeamActivity.this, "Team not found: Check Spelling", Toast.LENGTH_SHORT).show();
                        } else {
                            customDialogBar.HideDialog();
                            search = true;
                            Context context = imageView.getContext();
                            int imageID = context.getResources().getIdentifier("_"+teamNickName.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                            imageView.setImageResource(imageID);
                            resultsTextView.setText(teamInfo);
                            teamName = teamFullName;
                        }
                    }
                });
            }
        });

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogBar = new CustomDialogBar(SearchForTeamActivity.this);
                customDialogBar.ShowDialog("Fetching Team Information");
                search = false;
                nbaService.getTeam(teamEt.getText().toString(), new NBAService.TeamResponseListener() {
                    @Override
                    public void onError(String message) {
                        customDialogBar.HideDialog();
                        search = false;
                        resultsTextView.setText("");
                        imageView.setImageBitmap(null);
                        Toast.makeText(SearchForTeamActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String teamInfo, String teamNickName, String teamFullName, String teamID) {
                        if (teamInfo.equals("no input")) {
                            customDialogBar.HideDialog();
                            search = false;
                            resultsTextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForTeamActivity.this, "Please enter a team's name", Toast.LENGTH_SHORT).show();
                        } else if (teamInfo.equals("")) {
                            customDialogBar.HideDialog();
                            search = false;
                            resultsTextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForTeamActivity.this, "Team not found: Check Spelling", Toast.LENGTH_SHORT).show();
                        } else {
                            customDialogBar.HideDialog();
                            search = true;
                            Context context = imageView.getContext();
                            int imageID = context.getResources().getIdentifier("_"+teamNickName.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                            imageView.setImageResource(imageID);
                            resultsTextView.setText(teamInfo);
                            teamName = teamFullName;
                        }
                    }
                });
            }
        });

        addButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search) {
                    Add();
                    Toast.makeText(SearchForTeamActivity.this, "Added to your homepage!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SearchForTeamActivity.this, "Team Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Add() {
        String teamn = teamName;
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection(userID).document(teamn);
        Map<String, Object> team = new HashMap<>();
        team.put("favoriteTeams", teamn);
        team.put("display", "yes");
        documentReference.set(team).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                fStore.collection(userID).document("Favorites List").delete();
                Log.d("TAG", "onSuccess: profile is created for " + userID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "onSuccess: " + e.toString());
            }
        });
    }
}
