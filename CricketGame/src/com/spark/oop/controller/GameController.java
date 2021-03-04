package com.spark.oop.controller;

import com.spark.oop.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class GameController {

    private final static int NO_OF_TEAMS = 2;
    private final static int NO_OF_PLAYERS = 6;

    abstract protected int bat(Team battingTeam, int targetScore, Scanner scanner);

    public void playGame(){
        try(Scanner scanner = new Scanner(System.in)){
            List<Team> teams = new ArrayList<>();
            createTeams(teams, scanner);

            Team teamWonToss = getTeamWonToss(teams);
            Team teamLossToss = teams.get(0).getHasWonToss() ? teams.get(1) : teams.get(0);

            int targetScore = bat(teamWonToss, -1, scanner);
            int secondScore = bat(teamLossToss, targetScore, scanner);

            if (targetScore > secondScore) {
                System.out.println(
                        "Team " + teamWonToss.getName() + " has won the match by " + (targetScore - secondScore) + " runs.");
            }
            else if (targetScore < secondScore) {
                System.out.println(
                        "Team " + teamLossToss.getName() + " has won the match by " + (secondScore - targetScore) + " runs.");
            }
            else {
                System.out.println("Match is drawn");
            }
        } catch(Exception e){
            System.out.println("Game abruptly finished due to an error");
        }
    }

    private Team getTeamWonToss(List<Team> teams){
        Random random = new Random();

        Team wonToss = random.nextBoolean() ? teams.get(0) : teams.get(1);
        wonToss.setHasWonToss(true);

        return wonToss;
    }


    private void createTeams(List<Team> teams, Scanner scanner) {
        for(int i = 0; i < NO_OF_TEAMS; ++i){
            System.out.println("Enter team " + (i + 1) + " name : ");
            String input = scanner.nextLine();
            while(input == null || input.isEmpty()){
                System.out.println("Please enter a valid name : s");
                input = scanner.nextLine();
            }
            Team team = new Team(input, NO_OF_PLAYERS);
            teams.add(team);
        }
    }

    protected void getUserInputAndValidate(Scanner scanner) {
        System.out.println("Please press key P to play!:");
        String userInput = scanner.nextLine();

        while(userInput == null || !userInput.equalsIgnoreCase("p")){
            System.out.println("Invalid input. Please try again.");
            userInput = scanner.nextLine();
        }
    }
}
