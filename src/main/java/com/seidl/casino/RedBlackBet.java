/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seidl.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Áron
 */
public class RedBlackBet extends Bet {

    HashMap<String, ArrayList<Integer>> winnerCombos = new HashMap<>();
    String chosenColour;

    public RedBlackBet(String chosenColour, double amount) {
        super(amount);
        System.out.println(amount);
        this.chosenColour = chosenColour;
        winnerCombos.putIfAbsent("red", new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36)));
        winnerCombos.putIfAbsent("black", new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35)));
    }

    @Override
    public double wonAmount(int winnerNumber) {
        if (winnerCombos.get(chosenColour).contains(winnerNumber)) {
            return amount * 2;
        } else if (!("black".equals(chosenColour) || "red".equals(chosenColour))) {
            System.out.println("Érvénytelen színt adtál meg, úgyhogy ebből a körből kimaradsz!");
            return amount;
        } else {
            return 0;
        }
    }

}
