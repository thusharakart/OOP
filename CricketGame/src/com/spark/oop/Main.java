package com.spark.oop;

import com.spark.oop.controller.GameController;
import com.spark.oop.controller.implementation.FiveOverGameController;

public class Main {

    public static void main(String[] args) {
        GameController controller = new FiveOverGameController();
        controller.playGame();
    }
}
