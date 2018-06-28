/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seidl.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Áron
 */
public class RouletteGame {

    public static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int winnerNumber;
        System.out.println("Isten hozott Las Vegasban! Kérem adja meg, hogy játszani, vagy szimulálni szeretne!");
        System.out.println("(1: játék, 2: szimuláció)");
        int chosenGameMode = scanner.nextInt();
        if (!(chosenGameMode == 1 || chosenGameMode == 2)) {
            System.out.println("Ilyen játékmód nincs! Kérjük, add meg újra!");
            chosenGameMode = scanner.nextInt();
        }
        switch (chosenGameMode) {
            case 1:
                System.out.println("Kérlek add meg, mekkora össztőkével szeretnél játszani!");
                double money = scanner.nextDouble();
                UiPlayer p1 = new UiPlayer(money);
                players.add(p1);
                boolean iWannaPlayMore = true;
                while (iWannaPlayMore) {
                    if (players.isEmpty()) {
                        break;
                    }
                    layYourBets();
                    if (players.isEmpty()) {
                        break;
                    }
                    winnerNumber = spin();
                    pay(winnerNumber);
                    if (players.size() == 1 && players.get(0).getMoney() == 0) {
                        break;
                    }
                    System.out.println("Szeretnél még egy kört játszani? \n(y = yes, n = no)");
                    Scanner sc = new Scanner(System.in);
                    String answer = sc.nextLine();
                    if (answer.charAt(0) == 'n') {
                        System.out.println("Köszönjük, hogy a Seidl és társa kaszinó vendége volt! Viszontlátásra!");
                        iWannaPlayMore = false;
                    }

                }
                break;
            case 2:
                System.out.println("Mivel eddig csak Martingel típusú játékosunk van, két Martingellel fogunk játszani. Az egyik mindig a pirosra, a másik mindig a feketére fog rakni.");
                Martingel m1 = new Martingel("Béla", 100, "red", 100000);
                Martingel m2 = new Martingel("Jocó", 100, "black", 100000);
                players.add(m1);
                players.add(m2);
                System.out.println("Kérem adja meg, hogy hány kört szeretne játszani!");
                int numberOfMatch = scanner.nextInt();
                for (int i = 0; i < numberOfMatch; i++) {
                    if (players.isEmpty()) {
                        break;
                    }
                    layYourBets();
                    winnerNumber = spin();
                    pay(winnerNumber);
                    if (players.size() == 1 && players.get(0).getMoney() == 0) {
                        break;
                    }
                }
                break;
        }
    }

    public static void layYourBets() {
        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            try {
                Player player = it.next();
                player.nextBet();
            } catch (NoEnoughMoneyException ex) {
                System.out.println("Sajnos ma már nem tudsz többet játszani velünk! Köszönjük, hogy a vendégünk voltál! \nViszontlátásra!");
                it.remove();
            }
        }
    }

    public static int spin() {
        HashMap<String, ArrayList<Integer>> winnerCombos = new HashMap<>();
        winnerCombos.putIfAbsent("red", new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36)));
        winnerCombos.putIfAbsent("black", new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35)));
        int winnerNumber = (int) (Math.random() * 37);
        String winnerColour;
        if (winnerCombos.get("red").contains(winnerNumber)) {
            winnerColour = "piros";
        } else if (winnerCombos.get("black").contains(winnerNumber)) {
            winnerColour = "fekete";
        } else {
            winnerColour = "0";
        }
        if (winnerColour.equals("piros") || winnerColour.equals("fekete")) {
            System.out.println("A kipörgetett szám a " + winnerColour + " " + winnerNumber + "!");
        } else {
            System.out.println("A kipörgetett szám a " + winnerNumber + "!");
        }
        return winnerNumber;
    }

    public static void pay(int winnerNumber) {
        for (Player player : players) {
            player.pay(winnerNumber);
            player.betList.clear();
        }
    }
}
