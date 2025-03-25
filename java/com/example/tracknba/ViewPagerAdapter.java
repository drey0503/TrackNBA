package com.example.tracknba;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Map;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<ViewPagerModel> modelArrayList;

    public ViewPagerAdapter(Context context, ArrayList<ViewPagerModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_pager_item, container, false);
        ViewPagerModel viewPagerModel;
        String teamName;
        String teamWins;
        String teamLosses;
        String teamLogo;
        int imageID;

        TextView team_1_name_TV = view.findViewById(R.id.team_1_name_TV);
        TextView team_2_name_TV = view.findViewById(R.id.team_2_name_TV);
        TextView team_3_name_TV = view.findViewById(R.id.team_3_name_TV);
        TextView team_4_name_TV = view.findViewById(R.id.team_4_name_TV);
        TextView team_5_name_TV = view.findViewById(R.id.team_5_name_TV);
        TextView team_6_name_TV = view.findViewById(R.id.team_6_name_TV);
        TextView team_7_name_TV = view.findViewById(R.id.team_7_name_TV);
        TextView team_8_name_TV = view.findViewById(R.id.team_8_name_TV);
        TextView team_9_name_TV = view.findViewById(R.id.team_9_name_TV);
        TextView team_10_name_TV = view.findViewById(R.id.team_10_name_TV);
        TextView team_11_name_TV = view.findViewById(R.id.team_11_name_TV);
        TextView team_12_name_TV = view.findViewById(R.id.team_12_name_TV);
        TextView team_13_name_TV = view.findViewById(R.id.team_13_name_TV);
        TextView team_14_name_TV = view.findViewById(R.id.team_14_name_TV);
        TextView team_15_name_TV = view.findViewById(R.id.team_15_name_TV);

        TextView team_1_wins_TV = view.findViewById(R.id.team_1_wins_TV);
        TextView team_2_wins_TV = view.findViewById(R.id.team_2_wins_TV);
        TextView team_3_wins_TV = view.findViewById(R.id.team_3_wins_TV);
        TextView team_4_wins_TV = view.findViewById(R.id.team_4_wins_TV);
        TextView team_5_wins_TV = view.findViewById(R.id.team_5_wins_TV);
        TextView team_6_wins_TV = view.findViewById(R.id.team_6_wins_TV);
        TextView team_7_wins_TV = view.findViewById(R.id.team_7_wins_TV);
        TextView team_8_wins_TV = view.findViewById(R.id.team_8_wins_TV);
        TextView team_9_wins_TV = view.findViewById(R.id.team_9_wins_TV);
        TextView team_10_wins_TV = view.findViewById(R.id.team_10_wins_TV);
        TextView team_11_wins_TV = view.findViewById(R.id.team_11_wins_TV);
        TextView team_12_wins_TV = view.findViewById(R.id.team_12_wins_TV);
        TextView team_13_wins_TV = view.findViewById(R.id.team_13_wins_TV);
        TextView team_14_wins_TV = view.findViewById(R.id.team_14_wins_TV);
        TextView team_15_wins_TV = view.findViewById(R.id.team_15_wins_TV);

        TextView team_1_losses_TV = view.findViewById(R.id.team_1_losses_TV);
        TextView team_2_losses_TV = view.findViewById(R.id.team_2_losses_TV);
        TextView team_3_losses_TV = view.findViewById(R.id.team_3_losses_TV);
        TextView team_4_losses_TV = view.findViewById(R.id.team_4_losses_TV);
        TextView team_5_losses_TV = view.findViewById(R.id.team_5_losses_TV);
        TextView team_6_losses_TV = view.findViewById(R.id.team_6_losses_TV);
        TextView team_7_losses_TV = view.findViewById(R.id.team_7_losses_TV);
        TextView team_8_losses_TV = view.findViewById(R.id.team_8_losses_TV);
        TextView team_9_losses_TV = view.findViewById(R.id.team_9_losses_TV);
        TextView team_10_losses_TV = view.findViewById(R.id.team_10_losses_TV);
        TextView team_11_losses_TV = view.findViewById(R.id.team_11_losses_TV);
        TextView team_12_losses_TV = view.findViewById(R.id.team_12_losses_TV);
        TextView team_13_losses_TV = view.findViewById(R.id.team_13_losses_TV);
        TextView team_14_losses_TV = view.findViewById(R.id.team_14_losses_TV);
        TextView team_15_losses_TV = view.findViewById(R.id.team_15_losses_TV);

        ImageView team_1_logo = view.findViewById(R.id.team_1_logo);
        ImageView team_2_logo = view.findViewById(R.id.team_2_logo);
        ImageView team_3_logo = view.findViewById(R.id.team_3_logo);
        ImageView team_4_logo = view.findViewById(R.id.team_4_logo);
        ImageView team_5_logo = view.findViewById(R.id.team_5_logo);
        ImageView team_6_logo = view.findViewById(R.id.team_6_logo);
        ImageView team_7_logo = view.findViewById(R.id.team_7_logo);
        ImageView team_8_logo = view.findViewById(R.id.team_8_logo);
        ImageView team_9_logo = view.findViewById(R.id.team_9_logo);
        ImageView team_10_logo = view.findViewById(R.id.team_10_logo);
        ImageView team_11_logo = view.findViewById(R.id.team_11_logo);
        ImageView team_12_logo = view.findViewById(R.id.team_12_logo);
        ImageView team_13_logo = view.findViewById(R.id.team_13_logo);
        ImageView team_14_logo = view.findViewById(R.id.team_14_logo);
        ImageView team_15_logo = view.findViewById(R.id.team_15_logo);


        if (position == 0) {
            viewPagerModel = modelArrayList.get(0);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_1_name_TV.setText(teamName);
            team_1_wins_TV.setText(teamWins);
            team_1_losses_TV.setText(teamLosses);
            context = team_1_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_1_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(1);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_2_name_TV.setText(teamName);
            team_2_wins_TV.setText(teamWins);
            team_2_losses_TV.setText(teamLosses);
            context = team_2_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_2_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(2);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_3_name_TV.setText(teamName);
            team_3_wins_TV.setText(teamWins);
            team_3_losses_TV.setText(teamLosses);
            context = team_3_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_3_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(3);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_4_name_TV.setText(teamName);
            team_4_wins_TV.setText(teamWins);
            team_4_losses_TV.setText(teamLosses);
            context = team_4_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_4_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(4);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_5_name_TV.setText(teamName);
            team_5_wins_TV.setText(teamWins);
            team_5_losses_TV.setText(teamLosses);
            context = team_5_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_5_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(5);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_6_name_TV.setText(teamName);
            team_6_wins_TV.setText(teamWins);
            team_6_losses_TV.setText(teamLosses);
            context = team_6_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_6_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(6);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_7_name_TV.setText(teamName);
            team_7_wins_TV.setText(teamWins);
            team_7_losses_TV.setText(teamLosses);
            context = team_7_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_7_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(7);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_8_name_TV.setText(teamName);
            team_8_wins_TV.setText(teamWins);
            team_8_losses_TV.setText(teamLosses);
            context = team_8_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_8_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(8);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_9_name_TV.setText(teamName);
            team_9_wins_TV.setText(teamWins);
            team_9_losses_TV.setText(teamLosses);
            context = team_9_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_9_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(9);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_10_name_TV.setText(teamName);
            team_10_wins_TV.setText(teamWins);
            team_10_losses_TV.setText(teamLosses);
            context = team_10_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_10_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(10);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_11_name_TV.setText(teamName);
            team_11_wins_TV.setText(teamWins);
            team_11_losses_TV.setText(teamLosses);
            context = team_11_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_11_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(11);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_12_name_TV.setText(teamName);
            team_12_wins_TV.setText(teamWins);
            team_12_losses_TV.setText(teamLosses);
            context = team_12_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_12_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(12);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_13_name_TV.setText(teamName);
            team_13_wins_TV.setText(teamWins);
            team_13_losses_TV.setText(teamLosses);
            context = team_13_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_13_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(13);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_14_name_TV.setText(teamName);
            team_14_wins_TV.setText(teamWins);
            team_14_losses_TV.setText(teamLosses);
            context = team_14_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_14_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(14);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_15_name_TV.setText(teamName);
            team_15_wins_TV.setText(teamWins);
            team_15_losses_TV.setText(teamLosses);
            context = team_15_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_15_logo.setImageResource(imageID);

        } else if (position == 1) {
            viewPagerModel = modelArrayList.get(15);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_1_name_TV.setText(teamName);
            team_1_wins_TV.setText(teamWins);
            team_1_losses_TV.setText(teamLosses);
            context = team_1_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_1_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(16);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_2_name_TV.setText(teamName);
            team_2_wins_TV.setText(teamWins);
            team_2_losses_TV.setText(teamLosses);
            context = team_2_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_2_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(17);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_3_name_TV.setText(teamName);
            team_3_wins_TV.setText(teamWins);
            team_3_losses_TV.setText(teamLosses);
            context = team_3_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_3_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(18);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_4_name_TV.setText(teamName);
            team_4_wins_TV.setText(teamWins);
            team_4_losses_TV.setText(teamLosses);
            context = team_4_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_4_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(19);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_5_name_TV.setText(teamName);
            team_5_wins_TV.setText(teamWins);
            team_5_losses_TV.setText(teamLosses);
            context = team_5_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_5_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(20);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_6_name_TV.setText(teamName);
            team_6_wins_TV.setText(teamWins);
            team_6_losses_TV.setText(teamLosses);
            context = team_6_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_6_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(21);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_7_name_TV.setText(teamName);
            team_7_wins_TV.setText(teamWins);
            team_7_losses_TV.setText(teamLosses);
            context = team_7_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_7_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(22);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_8_name_TV.setText(teamName);
            team_8_wins_TV.setText(teamWins);
            team_8_losses_TV.setText(teamLosses);
            context = team_8_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_8_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(23);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_9_name_TV.setText(teamName);
            team_9_wins_TV.setText(teamWins);
            team_9_losses_TV.setText(teamLosses);
            context = team_9_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_9_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(24);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_10_name_TV.setText(teamName);
            team_10_wins_TV.setText(teamWins);
            team_10_losses_TV.setText(teamLosses);
            context = team_10_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_10_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(25);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_11_name_TV.setText(teamName);
            team_11_wins_TV.setText(teamWins);
            team_11_losses_TV.setText(teamLosses);
            context = team_11_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_11_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(26);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_12_name_TV.setText(teamName);
            team_12_wins_TV.setText(teamWins);
            team_12_losses_TV.setText(teamLosses);
            context = team_12_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_12_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(27);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_13_name_TV.setText(teamName);
            team_13_wins_TV.setText(teamWins);
            team_13_losses_TV.setText(teamLosses);
            context = team_13_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_13_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(28);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_14_name_TV.setText(teamName);
            team_14_wins_TV.setText(teamWins);
            team_14_losses_TV.setText(teamLosses);
            context = team_14_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_14_logo.setImageResource(imageID);

            viewPagerModel = modelArrayList.get(29);
            teamName = viewPagerModel.getTeamName();
            teamWins = viewPagerModel.getWins();
            teamLosses = viewPagerModel.getLosses();
            teamLogo = viewPagerModel.getLogo();
            team_15_name_TV.setText(teamName);
            team_15_wins_TV.setText(teamWins);
            team_15_losses_TV.setText(teamLosses);
            context = team_15_logo.getContext();
            imageID = context.getResources().getIdentifier("_"+teamLogo.replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            team_15_logo.setImageResource(imageID);

        }
        container.addView(view, position);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0) {
            return "Eastern Conference";
        } else if (position==1) {
            return "Western Conference";
        }
        return "";
    }
}
