package com.spark.oop.models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public static final String BATTING = "BATTING";
    public static final int MIN_SCORE = 4;
    private String name;
    private List<Player> players;
    private int totalScore;
    private int totalWickets;
    private int currentBatman;
    private boolean hasWonToss;

    public Team(String name, int noOfPlayers) {
        this.name = name;
        this.totalScore = 0;
        this.totalWickets = -1;
        this.currentBatman = -1;
        this.hasWonToss = false;
        setPlayers(noOfPlayers);
    }

    private void setPlayers(int noOfPlayers){
        this.players = new ArrayList<>();
        for(int i = 0; i < (noOfPlayers / 2); ++i){
            Player player = new Player(String.valueOf(i+1));
            players.add(player);
        }
        for(int i = (noOfPlayers / 2); i < noOfPlayers; ++i){
            Player player = new SuperPlayer(String.valueOf(i+1), MIN_SCORE);
            players.add(player);
        }
    }

    public String getName() {
        return name;
    }
    public int getTotalScore() {
        return this.totalScore;
    }
    public int getTotalWickets() {
        return this.totalWickets;
    }
    public boolean getHasWonToss() {
        return this.hasWonToss;
    }
    public void setHasWonToss(boolean hasWonToss){
        this.hasWonToss = hasWonToss;
    }

    public Player getNextPlayer() {
        this.currentBatman++;
        this.totalWickets++;
        if(this.currentBatman >= this.players.size()){
            return null;
        }
        Player nextPlayer = this.players.get(this.currentBatman);
        nextPlayer.setStatus(BATTING);
        return nextPlayer;
    }

    public void updateScore(int result) {
        this.totalScore += result;
    }

    public String getSummery() {
        return this.totalScore + "/" + this.totalWickets;
    }
}
