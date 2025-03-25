package com.example.tracknba;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PlayerGameList_RecyclerViewAdapter extends RecyclerView.Adapter<PlayerGameList_RecyclerViewAdapter.PlayerGameViewHolder> {
    Context context;
    ArrayList<PlayerGamesModel> playerGamesModels;
    final NBAService nbaService = new NBAService();

    private static final DecimalFormat df = new DecimalFormat("0.0");

    public PlayerGameList_RecyclerViewAdapter (Context context, ArrayList<PlayerGamesModel> playerGamesModels) {
        this.context = context;
        this.playerGamesModels = playerGamesModels;
    }


    @NonNull
    @Override
    public PlayerGameList_RecyclerViewAdapter.PlayerGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.player_game_list_item, parent, false);
        return new PlayerGameList_RecyclerViewAdapter.PlayerGameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerGameList_RecyclerViewAdapter.PlayerGameViewHolder holder, int position) {
        String playerTeamId;
        String homeTeamId;
        String awayTeamId;
        Context context;
        int imageID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date;
        playerTeamId = playerGamesModels.get(position).getPlayerTeamId();
        homeTeamId = playerGamesModels.get(position).getHomeTeam();
        awayTeamId = playerGamesModels.get(position).getAwayTeam();

        if (playerTeamId.equalsIgnoreCase(homeTeamId)) {
            holder.oppositionTextView.setText("vs");
            holder.oppositionNameTextView.setText(nbaService.teamAbbreviation.get(awayTeamId));
            context = holder.oppositionImageView.getContext();
            imageID = context.getResources().getIdentifier("_"+nbaService.teamAbbreviation.get(nbaService.teamAbbreviation.get(awayTeamId)).replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            holder.oppositionImageView.setImageResource(imageID);
            if (playerGamesModels.get(position).getHomeTeamScore()<playerGamesModels.get(position).getAwayTeamScore()) {
                holder.winlossTextView.setText("L");
                holder.winlossTextView.setTextColor(Color.RED);
            } else {
                holder.winlossTextView.setText("W");
                holder.winlossTextView.setTextColor(Color.GREEN);
            }
        } else {
            holder.oppositionTextView.setText("@");
            holder.oppositionNameTextView.setText(nbaService.teamAbbreviation.get(homeTeamId));
            context = holder.oppositionImageView.getContext();
            imageID = context.getResources().getIdentifier("_"+nbaService.teamAbbreviation.get(nbaService.teamAbbreviation.get(homeTeamId)).replaceAll(" ", "_").toLowerCase(), "drawable", context.getPackageName());
            holder.oppositionImageView.setImageResource(imageID);
            if (playerGamesModels.get(position).getAwayTeamScore() < playerGamesModels.get(position).getHomeTeamScore()) {
                holder.winlossTextView.setText("L");
                holder.winlossTextView.setTextColor(Color.RED);
            } else {
                holder.winlossTextView.setText("W");
                holder.winlossTextView.setTextColor(Color.GREEN);
            }
        }
        holder.scoreTextView.setText(playerGamesModels.get(position).getAwayTeamScore() + "-" + playerGamesModels.get(position).getHomeTeamScore());
        if (playerGamesModels.get(position).getMin().startsWith("0")) {
            holder.minutesTextView.setText(" " + playerGamesModels.get(position).getMin().charAt(1) + " MIN");
        } else {
            holder.minutesTextView.setText(playerGamesModels.get(position).getMin() + " MIN");
        }

        holder.gamedateTextView.setText(playerGamesModels.get(position).getGameDate().substring(5,7) + "/" +playerGamesModels.get(position).getGameDate().substring(8,10));
        try {
            date = sdf.parse(playerGamesModels.get(position).getGameDate().substring(0,10));
            sdf.applyPattern("EEEE");
            holder.gamedayTextView.setText(sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
            holder.gamedayTextView.setText("");
        }
        holder.pointsTextView.setText(playerGamesModels.get(position).getPts() + " PTS");
        holder.assistsTextView.setText(playerGamesModels.get(position).getAst() + " AST");
        holder.reboundsTextView.setText(playerGamesModels.get(position).getReb() + " REB");
        holder.stealsTextView.setText(playerGamesModels.get(position).getStl() + " STL");
        holder.blocksTextView.setText(playerGamesModels.get(position).getBlk() + " BLK");
        holder.turnoverTextView.setText(playerGamesModels.get(position).getTo() + " T.O.");
        if (!playerGamesModels.get(position).getPf().equalsIgnoreCase("6")) {
            holder.personalFoulTextView.setText("Personal Fouls: " + playerGamesModels.get(position).getPf());
        } else {
            holder.personalFoulTextView.setText("Fouled Out");
        }

        if (playerGamesModels.get(position).getFg_pct().equalsIgnoreCase("0.0")) {
            holder.fgPercentageTextView.setText("0.0 FG%");
        } else if (playerGamesModels.get(position).getFg_pct().equalsIgnoreCase("100.0")){
            holder.fgPercentageTextView.setText("100 FG%");
        }  else {
            holder.fgPercentageTextView.setText(playerGamesModels.get(position).getFg_pct().substring(0,4) + " FG%");
        }

        if (playerGamesModels.get(position).getFg3_pct().equalsIgnoreCase("0.0")) {
            holder.threePointPercentageTextView.setText("0.0 3P%");
        } else if (playerGamesModels.get(position).getFg3_pct().equalsIgnoreCase("100.0")){
            holder.threePointPercentageTextView.setText("100 3P%");
        } else {
            holder.threePointPercentageTextView.setText(playerGamesModels.get(position).getFg3_pct().substring(0,4) + " 3P%");
        }

        if (playerGamesModels.get(position).getFt_pct().equalsIgnoreCase("0.0") || playerGamesModels.get(position).getFt_pct().equalsIgnoreCase("null")) {
            holder.freeThrowPercentageTextView.setText("0.0 FT%");
        } else if (playerGamesModels.get(position).getFt_pct().equalsIgnoreCase("100.0")){
            holder.freeThrowPercentageTextView.setText("100 FT%");
        } else {
            holder.freeThrowPercentageTextView.setText(playerGamesModels.get(position).getFt_pct().substring(0,4) + " FT%");
        }
        playerTeamId="";
        homeTeamId="";
        awayTeamId="";
    }

    @Override
    public int getItemCount() {
        return playerGamesModels.size();
    }

    public static class PlayerGameViewHolder extends RecyclerView.ViewHolder {
        ImageView oppositionImageView;
        TextView gamedateTextView, gamedayTextView, oppositionTextView,  oppositionNameTextView, winlossTextView, scoreTextView, minutesTextView,
                pointsTextView, assistsTextView, reboundsTextView, stealsTextView, blocksTextView, personalFoulTextView,
                threePointPercentageTextView, fgPercentageTextView, turnoverTextView,freeThrowPercentageTextView;

        public PlayerGameViewHolder(@NonNull View itemView) {
            super(itemView);
            gamedayTextView = itemView.findViewById(R.id.gamedayTV);
            gamedateTextView = itemView.findViewById(R.id.gamedateTV);
            oppositionTextView = itemView.findViewById(R.id.oppTV);
            oppositionImageView = itemView.findViewById(R.id.oppImageView);
            oppositionNameTextView = itemView.findViewById(R.id.oppTeamTV);
            winlossTextView = itemView.findViewById(R.id.winlossTV);
            scoreTextView = itemView.findViewById(R.id.scoreTV);
            minutesTextView = itemView.findViewById(R.id.minutesTV);
            pointsTextView = itemView.findViewById(R.id.pointsTV);
            assistsTextView = itemView.findViewById(R.id.assitsTV);
            reboundsTextView = itemView.findViewById(R.id.reboundsTV);
            stealsTextView = itemView.findViewById(R.id.stealsTV);
            blocksTextView = itemView.findViewById(R.id.blocksTV);
            personalFoulTextView = itemView.findViewById(R.id.personalFoulTV);
            threePointPercentageTextView = itemView.findViewById(R.id.threepointTV);
            fgPercentageTextView = itemView.findViewById(R.id.fieldgoalTV);
            turnoverTextView = itemView.findViewById(R.id.turnoverTV);
            freeThrowPercentageTextView = itemView.findViewById(R.id.freethrowTV);
        }
    }
}
