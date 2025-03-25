package com.example.tracknba;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.net.URL;

public class SearchForPlayerActivity extends AppCompatActivity {
    public static final String PLAYER_IMAGE = "https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/";
    String player_FullName;
    TextView result_TextView;
    AutoCompleteTextView player_EditText;
    ImageView imageView;
    Button search_btn, addButn;
    boolean search = false;
    protected CustomDialogBar customDialogBar;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    final NBAService nbaService = new NBAService(SearchForPlayerActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_player);
        player_EditText = findViewById(R.id.player_et);
        result_TextView = findViewById(R.id.results);
        search_btn = findViewById(R.id.btn_search);
        addButn = findViewById(R.id.addBtn);

        imageView = findViewById(R.id.imageView);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        nbaService.getAllPlayers(new NBAService.PlayerListResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(SearchForPlayerActivity.this, "Could not retrieve list of players", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ArrayList<String> playerList) {
                String[] listOfPlayer = new String[playerList.size()];
                listOfPlayer = playerList.toArray(listOfPlayer);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchForPlayerActivity.this, android.R.layout.simple_list_item_1, listOfPlayer);
                player_EditText.setAdapter(adapter);
                player_EditText.setTextColor(Color.BLACK);
            }
        });


        player_EditText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    customDialogBar = new CustomDialogBar(SearchForPlayerActivity.this);
                    customDialogBar.ShowDialog("Fetching Player Information");
                    search = false;
                    nbaService.getPlayerStats(player_EditText.getText().toString(), new NBAService.PlayerStatsResponseListener() {
                        @Override
                        public void onError(String message) {
                            customDialogBar.HideDialog();
                            search = false;
                            result_TextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForPlayerActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onResponse(String playerName, String displayMessage) {
                            if (playerName.equals("gibberish")) {
                                customDialogBar.HideDialog();
                                search = false;
                                result_TextView.setText("");
                                imageView.setImageBitmap(null);
                                Toast.makeText(SearchForPlayerActivity.this, "Player not found: Check Spelling", Toast.LENGTH_SHORT).show();
                            } else if (playerName.equals("")) {
                                customDialogBar.HideDialog();
                                search = false;
                                result_TextView.setText("");
                                imageView.setImageBitmap(null);
                                Toast.makeText(SearchForPlayerActivity.this, "Please enter a player's name", Toast.LENGTH_SHORT).show();
                            } else if (playerName.equals("more than 1")) {
                                customDialogBar.HideDialog();
                                search = false;
                                result_TextView.setText("");
                                imageView.setImageBitmap(null);
                                Toast.makeText(SearchForPlayerActivity.this, "Please specify player's name", Toast.LENGTH_SHORT).show();
                            } else {
                                nbaService.getPlayerImage(playerName, new NBAService.PlayerImageResponseListener() {
                                    @Override
                                    public void onError(String message) {
                                        Toast.makeText(SearchForPlayerActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                    @Override
                                    public void onResponse(String playerImageID) {
                                        String url = PLAYER_IMAGE + playerImageID + ".png";
                                        new ImageLoadTask(url, imageView).execute();
                                        customDialogBar.HideDialog();
                                    }
                                });
                                search = true;
                                result_TextView.setText(displayMessage);
                                player_FullName = playerName;
                            }
                        }
                    });
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogBar = new CustomDialogBar(SearchForPlayerActivity.this);
                customDialogBar.ShowDialog("Fetching Player Information");
                search = false;
                nbaService.getPlayerStats(player_EditText.getText().toString(), new NBAService.PlayerStatsResponseListener() {
                    @Override
                    public void onError(String message) {
                        customDialogBar.HideDialog();
                        search = false;
                        result_TextView.setText("");
                        imageView.setImageBitmap(null);
                        Toast.makeText(SearchForPlayerActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String playerName, String displayMessage) {
                        if (playerName.equals("gibberish")) {
                            customDialogBar.HideDialog();
                            search = false;
                            result_TextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForPlayerActivity.this, "Player not found: Check Spelling", Toast.LENGTH_SHORT).show();
                        } else if (playerName.equals("")) {
                            customDialogBar.HideDialog();
                            search = false;
                            result_TextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForPlayerActivity.this, "Please enter a player's name", Toast.LENGTH_SHORT).show();
                        } else if (playerName.equals("more than 1")) {
                            customDialogBar.HideDialog();
                            search = false;
                            result_TextView.setText("");
                            imageView.setImageBitmap(null);
                            Toast.makeText(SearchForPlayerActivity.this, "Please specify player's name", Toast.LENGTH_SHORT).show();
                        } else {
                            nbaService.getPlayerImage(playerName, new NBAService.PlayerImageResponseListener() {
                                @Override
                                public void onError(String message) {
                                    Toast.makeText(SearchForPlayerActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                                @Override
                                public void onResponse(String playerImageID) {
                                    String url = PLAYER_IMAGE + playerImageID + ".png";
                                    new ImageLoadTask(url, imageView).execute();
                                    customDialogBar.HideDialog();
                                }
                            });
                            search = true;
                            result_TextView.setText(displayMessage);
                            player_FullName = playerName;
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
                    Toast.makeText(SearchForPlayerActivity.this, "Added to your homepage!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SearchForPlayerActivity.this, "Please search for a player first \n(Click Search)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Add() {
        String playern = player_FullName;
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection(userID).document(playern);
        Map<String,Object> team = new HashMap<>();
        team.put("favoritePlayers", playern);
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

    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }
}