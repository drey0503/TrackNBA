package com.example.tracknba;

public class FavoriteListModel {

    private String favoritePlayers;
    private String favoriteTeams;

    private FavoriteListModel() {
    }

    private FavoriteListModel(String favoritePlayers, String favoriteTeams) {
        this.favoritePlayers = favoritePlayers;
        this.favoriteTeams = favoriteTeams;
    }

    public String getFavoritePlayers() {
        return favoritePlayers;
    }

    public String getFavoriteTeams() {
        return favoriteTeams;
    }

    public void setFavoriteTeams(String favoriteTeams) {
        this.favoriteTeams = favoriteTeams;
    }

    public void setFavoritePlayers(String favoritePlayers) {
        this.favoritePlayers = favoritePlayers;
    }
}
