package com.example.tracknba;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameList_RecyclerViewAdapter extends RecyclerView.Adapter<GameList_RecyclerViewAdapter.GameViewHolder> {
    Context context;
    ArrayList<GamesModel> gamesModels;

    public GameList_RecyclerViewAdapter (Context context, ArrayList<GamesModel> gamesModels) {
        this.context = context;
        this.gamesModels = gamesModels;
    }

    @NonNull
    @Override
    public GameList_RecyclerViewAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.game_list_item, parent, false);
        return new GameList_RecyclerViewAdapter.GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameList_RecyclerViewAdapter.GameViewHolder holder, int position) {
        String[][] awayScoreLines = gamesModels.get(position).getAwayTeamScore();
        String[][] homeScoreLines = gamesModels.get(position).getHomeTeamScore();

        if (gamesModels.get(position).getTotalQuarters() == 4) {
            holder.quartersTextView.setText("     1        2        3       4");

            if (gamesModels.get(position).getGamePeriods() == 0) {
                holder.finalTextView.setText("Final");
            } else if (gamesModels.get(position).isHalftimeStatus()) {
                holder.finalTextView.setText("Halftime");
            } else if (gamesModels.get(position).isGamePeriodStatus()) {
                holder.finalTextView.setText("End Q" + gamesModels.get(position).getGamePeriods());
            } else if (gamesModels.get(position).getGameClock().equalsIgnoreCase("null")){
                holder.finalTextView.setText("End Q" + gamesModels.get(position).getGamePeriods());
            } else {
                holder.finalTextView.setText("Q" + gamesModels.get(position).getGamePeriods() + " - " + gamesModels.get(position).getGameClock());
            }

            holder.awayScoreLineTextView.setText("    " + awayScoreLines[position][0] + "     " + awayScoreLines[position][1] + "      " + awayScoreLines[position][2] + "     " + awayScoreLines[position][3]);

            holder.homeScoreLineTextView.setText("    " + homeScoreLines[position][0] + "     " + homeScoreLines[position][1] + "      " + homeScoreLines[position][2] + "     " + homeScoreLines[position][3]);

        } else if (gamesModels.get(position).getTotalQuarters() == 5) {
            holder.quartersTextView.setText("   1      2      3      4     OT");

            if (gamesModels.get(position).getGamePeriods() == 0) {
                holder.finalTextView.setText("Final/OT");
            } else if (gamesModels.get(position).isGamePeriodStatus()) {
                if (gamesModels.get(position).getGamePeriods() == 4) {
                    holder.finalTextView.setText("End Q" + gamesModels.get(position).getGamePeriods());
                } else {
                    holder.finalTextView.setText("End OT");
                }
            } else if (gamesModels.get(position).getGameClock().equalsIgnoreCase("null")) {
                if (gamesModels.get(position).getGamePeriods() == 4) {
                    holder.finalTextView.setText("End Q" + gamesModels.get(position).getGamePeriods());
                } else {
                    holder.finalTextView.setText("End OT");
                }
            } else {
                holder.finalTextView.setText("OT - " + gamesModels.get(position).getGameClock());
            }

            int awayOT = awayScoreLines[position][4].length();
            if (awayOT == 1) {
                holder.awayScoreLineTextView.setText("  " + awayScoreLines[position][0] + "   " + awayScoreLines[position][1] + "    " + awayScoreLines[position][2] + "    " + awayScoreLines[position][3] + "     " + awayScoreLines[position][4]);
            } else {
                holder.awayScoreLineTextView.setText("  " + awayScoreLines[position][0] + "   " + awayScoreLines[position][1] + "    " + awayScoreLines[position][2] + "    " + awayScoreLines[position][3] + "    " + awayScoreLines[position][4]);
            }

            int homeOT = homeScoreLines[position][4].length();
            if (homeOT == 1) {
                holder.homeScoreLineTextView.setText("  " + homeScoreLines[position][0] + "   " + homeScoreLines[position][1] + "    " + homeScoreLines[position][2] + "    " + homeScoreLines[position][3] + "     " + homeScoreLines[position][4]);
            } else {
                holder.homeScoreLineTextView.setText("  " + homeScoreLines[position][0] + "   " + homeScoreLines[position][1] + "    " + homeScoreLines[position][2] + "    " + homeScoreLines[position][3] + "    " + homeScoreLines[position][4]);
            }

        } else if (gamesModels.get(position).getTotalQuarters() == 6){
            holder.quartersTextView.setText("  1    2     3    4   OT  OT2");

            if (gamesModels.get(position).getGamePeriods() == 0) {
                holder.finalTextView.setText("Final/OT2");
            } else if (gamesModels.get(position).isGamePeriodStatus()) {
                if (gamesModels.get(position).getGamePeriods() == 5) {
                    holder.finalTextView.setText("End OT");
                } else {
                    holder.finalTextView.setText("End OT2");
                }
            } else if (gamesModels.get(position).getGameClock().equalsIgnoreCase("null")) {
                if (gamesModels.get(position).getGamePeriods() == 5) {
                    holder.finalTextView.setText("End OT");
                } else {
                    holder.finalTextView.setText("End OT2");
                }
            } else {
                holder.finalTextView.setText("OT2 - " + gamesModels.get(position).getGameClock());
            }

            int awayOT = awayScoreLines[position][4].length();
            int awayOT2 = awayScoreLines[position][5].length();
            String awayOT1 = " " + awayScoreLines[position][0] + "  " + awayScoreLines[position][1] + "  " + awayScoreLines[position][2] + "  " + awayScoreLines[position][3] + "  " + awayScoreLines[position][4] + "    ";
            if (awayOT == 1) {
                awayOT1 = " " + awayScoreLines[position][0] + "  " + awayScoreLines[position][1] + "  " + awayScoreLines[position][2] + "  " + awayScoreLines[position][3] + "   " + awayScoreLines[position][4] + "    ";
                if (awayOT2 == 1) {
                    holder.awayScoreLineTextView.setText(awayOT1 + " " + awayScoreLines[position][5]);
                } else {
                    holder.awayScoreLineTextView.setText(awayOT1 + awayScoreLines[position][5]);
                }
            } else if (awayOT2 == 1){
                holder.awayScoreLineTextView.setText(awayOT1 + " " + awayScoreLines[position][5]);
            }
            int homeOT = homeScoreLines[position][4].length();
            int homeOT2 = homeScoreLines[position][5].length();
            String homeOT1 = " " + homeScoreLines[position][0] + "  " + homeScoreLines[position][1] + "  " + homeScoreLines[position][2] + "  " + homeScoreLines[position][3] + "  " + homeScoreLines[position][4] + "    ";
            if (homeOT == 1) {
                homeOT1 = " " + homeScoreLines[position][0] + "  " + homeScoreLines[position][1] + "  " + homeScoreLines[position][2] + "  " + homeScoreLines[position][3] + "   " + homeScoreLines[position][4] + "    ";
                if (homeOT2 == 1) {
                    holder.homeScoreLineTextView.setText(homeOT1 + " " + homeScoreLines[position][5]);
                } else {
                    holder.homeScoreLineTextView.setText(homeOT1 + homeScoreLines[position][5]);
                }
            } else if (homeOT2 == 1){
                holder.homeScoreLineTextView.setText(homeOT1 + " " + homeScoreLines[position][5]);
            }
        }

        holder.awayTeamNameTextView.setText(gamesModels.get(position).getAwayTeamName());
        holder.homeTeamNameTextView.setText(gamesModels.get(position).getHomeTeamName());

        holder.awayTeamFinalTextView.setText(gamesModels.get(position).getAwayTeamFinalScore());
        holder.homeTeamFinalTextView.setText(gamesModels.get(position).getHomeTeamFinalScore());

        int awayScore = Integer.parseInt(gamesModels.get(position).getAwayTeamFinalScore());
        int homeScore = Integer.parseInt(gamesModels.get(position).getHomeTeamFinalScore());
        if (gamesModels.get(position).getGamePeriods() == 0) {
            if (awayScore > homeScore) {
                holder.awayTeamNameTextView.setTypeface(null, Typeface.BOLD);
                holder.awayTeamFinalTextView.setTypeface(null, Typeface.BOLD);
                holder.homeTeamNameTextView.setTypeface(null, Typeface.NORMAL);
                holder.homeTeamFinalTextView.setTypeface(null, Typeface.NORMAL);
            }
            if (awayScore < homeScore){
                holder.homeTeamNameTextView.setTypeface(null, Typeface.BOLD);
                holder.homeTeamFinalTextView.setTypeface(null, Typeface.BOLD);
                holder.awayTeamNameTextView.setTypeface(null, Typeface.NORMAL);
                holder.awayTeamFinalTextView.setTypeface(null, Typeface.NORMAL);
            }
        } else {
            holder.awayTeamNameTextView.setTypeface(null, Typeface.NORMAL);
            holder.awayTeamFinalTextView.setTypeface(null, Typeface.NORMAL);
            holder.homeTeamNameTextView.setTypeface(null, Typeface.NORMAL);
            holder.homeTeamFinalTextView.setTypeface(null, Typeface.NORMAL);
        }

        holder.awayTeamRecordTextView.setText("(" + gamesModels.get(position).getAwayTeamTotalRecord() + ", " + gamesModels.get(position).getAwayTeamAwayRecord() + " Away)");
        holder.homeTeamRecordTextView.setText("(" + gamesModels.get(position).getHomeTeamTotalRecord() + ", " + gamesModels.get(position).getHomeTeamHomeRecord() + " Home)");
        holder.awayTeamImageView.setImageResource(gamesModels.get(position).getAwayTeamImageID());
        holder.homeTeamImageView.setImageResource(gamesModels.get(position).getHomeTeamImageID());
    }

    @Override
    public int getItemCount() {
        return gamesModels.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView awayTeamImageView, homeTeamImageView;
        TextView awayTeamNameTextView, homeTeamNameTextView, awayTeamRecordTextView,
                homeTeamRecordTextView, quartersTextView,
                awayScoreLineTextView, homeScoreLineTextView, finalTextView,
                awayTeamFinalTextView, homeTeamFinalTextView;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            awayTeamImageView = itemView.findViewById(R.id.awayTeamImageView);
            homeTeamImageView = itemView.findViewById(R.id.homeTeamImageView);
            awayTeamNameTextView = itemView.findViewById(R.id.awayTeamNameTextView);
            homeTeamNameTextView = itemView.findViewById(R.id.homeTeamNameTextView);
            awayTeamRecordTextView = itemView.findViewById(R.id.awayTeamRecordTextView);
            homeTeamRecordTextView = itemView.findViewById(R.id.homeTeamRecordTextView);
            quartersTextView = itemView.findViewById(R.id.quartersTextView);
            awayScoreLineTextView = itemView.findViewById(R.id.awayScoreLineTextView);
            homeScoreLineTextView = itemView.findViewById(R.id.homeScoreLineTextView);
            finalTextView = itemView.findViewById(R.id.finalTextView);
            awayTeamFinalTextView = itemView.findViewById(R.id.awayTeamFinalTextView);
            homeTeamFinalTextView = itemView.findViewById(R.id.homeTeamFinalTextView);
        }
    }
}
