package com.example.tracknba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class StandingsActivity extends AppCompatActivity {
    final NBAService nbaService = new NBAService(StandingsActivity.this);
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<ViewPagerModel> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        loadTextView();
    }

    private void loadTextView() {
        modelArrayList = new ArrayList<>();
        nbaService.getTeamStandings(new NBAService.TeamStandingsResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(StandingsActivity.this, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Map<String, String[]> standings) {
                String[] teamInfo;
                for (int i = 1; i < 16; i++) {
                    teamInfo = standings.get("east_" + i).clone();
                    modelArrayList.add(new ViewPagerModel(teamInfo[0], teamInfo[1], teamInfo[2], teamInfo[3], teamInfo[4]));
                }
                for (int j = 1; j < 16; j++) {
                    teamInfo = standings.get("west_" + j).clone();
                    modelArrayList.add(new ViewPagerModel(teamInfo[0], teamInfo[1], teamInfo[2], teamInfo[3], teamInfo[4]));
                }
//                String[] teamInfo = standings.get("east_1").clone();
//                modelArrayList.add(new ViewPagerModel(teamInfo[0], teamInfo[1], teamInfo[2], teamInfo[3], 0));
//                teamInfo = standings.get("east_2").clone();
//                modelArrayList.add(new ViewPagerModel(teamInfo[0], teamInfo[1], teamInfo[2], teamInfo[3], 0));
                viewPagerAdapter = new ViewPagerAdapter(StandingsActivity.this, modelArrayList);

                tabLayout.setupWithViewPager(viewPager);

                viewPager.setAdapter(viewPagerAdapter);
            }
        });
//        modelArrayList.add(new ViewPagerModel("1", "Miami Heat", "55", "27", 4));
//        modelArrayList.add(new ViewPagerModel("2", "Bucks", "54", "28", 5));
//        viewPagerAdapter = new ViewPagerAdapter(this, modelArrayList);
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        viewPager.setAdapter(viewPagerAdapter);
    }
}