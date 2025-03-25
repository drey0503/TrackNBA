package com.example.tracknba;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracknba.databinding.ActivityMainBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;
    String userID;
    final NBAService nbaService = new NBAService(MainActivity.this);

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
            SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember", "false");
            editor.apply();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        });


//        CollectionReference collection = firebaseFirestore.collection(userID);
//        AggregateQuerySnapshot snapshot = collection.count().get().get();
//        System.out.println("Count: " + snapshot.getCount());


        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_chat, R.id.nav_compare_players, R.id.nav_compare_teams,
                R.id.nav_search_for_player, R.id.nav_search_for_team, R.id.nav_settings).setOpenableLayout(drawer).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();
        mFirestoreList = findViewById(R.id.firestore_list);
        Query query = firebaseFirestore.collection(userID).whereEqualTo("display", "yes");

//        SwipeableRecyclerViewTouchListener swipeTouchListener =
//                new SwipeableRecyclerViewTouchListener(mRecyclerView,
//                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
//                            @Override
//                            public boolean canSwipe(int position) {
//                                return true;
//                            }
//
//                            @Override
//                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
//                                for (int position : reverseSortedPositions) {
//                                    mItems.remove(position);
//                                    mAdapter.notifyItemRemoved(position);
//                                }
//                                mAdapter.notifyDataSetChanged();
//                            }
//
//                            @Override
//                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
//                                for (int position : reverseSortedPositions) {
//                                    mItems.remove(position);
//                                    mAdapter.notifyItemRemoved(position);
//                                }
//                                mAdapter.notifyDataSetChanged();
//                            }
//                        });
//
//        mRecyclerView.addOnItemTouchListener(swipeTouchListener);
//    }


        FirestoreRecyclerOptions<FavoriteListModel> options = new FirestoreRecyclerOptions.Builder<FavoriteListModel>()
                .setQuery(query, FavoriteListModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<FavoriteListModel, FavoriteViewHolder>(options) {
            @NonNull
            @Override
            public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new FavoriteViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position, @NonNull FavoriteListModel model) {
                if (model.getFavoritePlayers() != null) {
                    nbaService.getPlayerImage(model.getFavoritePlayers(), new NBAService.PlayerImageResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String playerImageID) {
                            String url = nbaService.PLAYER_IMAGE + playerImageID + ".png";
                            new MainActivity.ImageLoadTask(url, holder.list_image).execute();
                        }
                    });
                    holder.list_name.setText(model.getFavoritePlayers());
                } else if (model.getFavoriteTeams() != null) {
                    String teamNickName = nbaService.teamCode.get(model.getFavoriteTeams());
                    Context context = holder.list_image.getContext();
                    int imageID = context.getResources().getIdentifier("_"+ teamNickName.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
                    holder.list_image.setImageResource(imageID);
                    holder.list_name.setText(model.getFavoriteTeams());
                } else {
                    holder.list_image.setImageBitmap(null);
                    holder.list_name.setText("");
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i;
                        i = new Intent(MainActivity.this, FavoriteViewActivity.class);
                        i.putExtra("Favorite Text", holder.list_name.getText().toString());
                        startActivity(i);
                    }
                });
            }
        };
        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    private class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView list_name;
        private ImageView list_image;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_text);
            list_image = itemView.findViewById(R.id.list_image);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        adapter.startListening();
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    public void updateNavHeader() {
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        View headerView= navigationView.getHeaderView(0);
//        TextView navName= headerView.findViewById(R.id.txtName);
//        TextView navEmail=headerView.findViewById(R.id.txtEmail);
//
//        navName.setText(currentUser.getDisplayName());
//        navEmail.setText(currentUser.getEmail());
//
//    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//        return true;
//    }

}