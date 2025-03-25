package com.example.tracknba;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FavoriteViewActivity extends AppCompatActivity {

    ArrayList<GamesModel> gamesModels = new ArrayList<>();
    ArrayList<PlayerGamesModel> playerGamesModels = new ArrayList<>();
    final NBAService nbaService = new NBAService(FavoriteViewActivity.this);
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_view);
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);

        recyclerView = findViewById(R.id.gameRecyclerView);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        String retrievedData = FavoriteViewActivity.this.getIntent().getStringExtra("Favorite Text");

        if (nbaService.teamCode.containsKey(retrievedData)) {
            textView.setText("");
            nbaService.getTeam(retrievedData, new NBAService.TeamResponseListener() {
                @Override
                public void onError(String message) {
                    textView.setText("");
                    imageView.setImageBitmap(null);
                    Toast.makeText(FavoriteViewActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(String teamInfo, String teamNickName, String teamFullName, String teamID) {
                    textView.setText(teamInfo);
                    textView.setTextSize(17);
                    Context context = imageView.getContext();
                    int imageID = context.getResources().getIdentifier("_"+teamNickName.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                    imageView.setImageResource(imageID);
                    nbaService.getGames(teamID, new NBAService.GameResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onResponse(String[] awayTeams, String[] homeTeams, String[][] awayScoreLines, String[][] homeScoreLines, String[] awayFinalScores, String[] homeFinalScores, int[] totalQuarters, String[] gameClock, boolean[] halftimeStatus, boolean[] gamePeriodStatus, int[] gamePeriods, String[] awayTeamAwayRecord, String[] awayTeamHomeRecord, String[] awayTeamTotalRecord, String[] homeTeamAwayRecord, String[] homeTeamHomeRecord, String[] homeTeamTotalRecord) {
                            for (int i = 0; i < awayTeams.length; i++) {
                                int awayTeamImageID = context.getResources().getIdentifier("_"+awayTeams[i].replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                                int homeTeamImageID = context.getResources().getIdentifier("_"+homeTeams[i].replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                                gamesModels.add(new GamesModel(awayTeams[i], homeTeams[i], "", "",
                                        awayScoreLines, homeScoreLines, awayFinalScores[i], homeFinalScores[i], awayTeamImageID,
                                        homeTeamImageID, totalQuarters[i], gameClock[i], halftimeStatus[i], gamePeriodStatus[i],
                                        gamePeriods[i], awayTeamAwayRecord[i], awayTeamHomeRecord[i], awayTeamTotalRecord[i],
                                        homeTeamAwayRecord[i], homeTeamHomeRecord[i], homeTeamTotalRecord[i]));
                            }
                            GameList_RecyclerViewAdapter adapter = new GameList_RecyclerViewAdapter(FavoriteViewActivity.this, gamesModels);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteViewActivity.this));
                        }
                    });
                }
            });
        } else {
            textView.setText("");
            nbaService.getPlayerStats(retrievedData, new NBAService.PlayerStatsResponseListener() {
                @Override
                public void onError(String message) {
                    textView.setText("");
                    imageView.setImageBitmap(null);
                    Toast.makeText(FavoriteViewActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(String playerName, String result) {
                    textView.setText(result);
                    nbaService.getPlayerImage(playerName, new NBAService.PlayerImageResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(FavoriteViewActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String playerImageID) {
                            String url = nbaService.PLAYER_IMAGE + playerImageID + ".png";
                            new FavoriteViewActivity.ImageLoadTask(url, imageView).execute();
                        }
                    });

                    nbaService.getPlayersGameStats(playerName, new NBAService.PlayersGamesResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(FavoriteViewActivity.this, "Could not load games", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String[] playerTeamId, String[] homeTeams, String[] awayTeams, int[] homeTeamScores, int[] awayTeamScores, String[] gameDates, String[] mins, String[] pts, String[] reb, String[] ast, String[] stl, String[] blk, String[] pf, String[] to, String[] fg_pct, String[] fg3_pct, String[] ft_pct) {
                            for (int i = 9; i >= 0; i--) {
                                playerGamesModels.add(new PlayerGamesModel(playerTeamId[i], homeTeams[i], awayTeams[i], homeTeamScores[i], awayTeamScores[i], gameDates[i], mins[i], pts[i], reb[i], ast[i], stl[i], blk[i], pf[i], to[i], fg_pct[i], fg3_pct[i], ft_pct[i]));
                            }
                            PlayerGameList_RecyclerViewAdapter adapter = new PlayerGameList_RecyclerViewAdapter(FavoriteViewActivity.this, playerGamesModels);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(FavoriteViewActivity.this));
                        }
                    });
                }
            });
        }
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorite_view_options_menu, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove:
                Delete();
                Toast.makeText(FavoriteViewActivity.this, "Removed from your favorite list!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Delete() {
        String teamn = FavoriteViewActivity.this.getIntent().getStringExtra("Favorite Text");;
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection(userID).document(teamn);
        documentReference.delete();
    }

}
