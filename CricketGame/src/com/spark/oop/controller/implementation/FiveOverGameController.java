package com.spark.oop.controller.implementation;

import com.spark.oop.controller.GameController;
import com.spark.oop.models.Player;
import com.spark.oop.models.Team;

import java.text.MessageFormat;
import java.util.Random;
import java.util.Scanner;

public class FiveOverGameController extends GameController {

    public static final String OUT = "OUT";
    public static final String BOWLED = "BOWLED";
    public static final String CAUGHT = "CAUGHT";
    private final static int NO_OF_OVERS = 5;
    private final static int NO_OF_BALLS = 3;

    @Override
    protected int bat(Team battingTeam, int targetScore, Scanner scanner) {
        System.out.println("Team " + battingTeam.getName() + " is batting.");

        int totNoOfBalls = NO_OF_OVERS * NO_OF_BALLS;

        Random random = new Random();

        Player player = battingTeam.getNextPlayer();

        for (int ball = 0; ball < totNoOfBalls; ++ball) {
            getUserInputAndValidate(scanner);
            int result = player.bat(random);

            if (result == 5 || result == 7) {
                System.out.println("Player " + player.getName() + " is out!");
                player.setStatus(OUT);
                player.setGotOutBy(result == 5 ? BOWLED : CAUGHT);

                player = battingTeam.getNextPlayer();

                if (player == null) {
                    System.out.println("All out for team " + battingTeam.getName());
                    break;
                }
                result = 0; // because the player get out, runs should be zero.
            } else {
                System.out.println(
                        result == 0 ? "Dot Ball" : result + " runs were scored by the player " + player.getName());
                player.updateScore(result);
            }
            battingTeam.updateScore(result);

            String overs = MessageFormat.format("({0}/{1})", (ball+1) / NO_OF_BALLS, (ball+1) % NO_OF_BALLS);
            String teamSummery = battingTeam.getSummery();

            System.out.println("Score summery : " + overs + " " + teamSummery);

            if ((ball+1) % NO_OF_BALLS == 0 && ball != 0) {
                System.out.println("End of over!");
            }
            if (targetScore > -1 && battingTeam.getTotalScore() > targetScore) {
                break;
            }
        }
        return battingTeam.getTotalScore();
    }
}
