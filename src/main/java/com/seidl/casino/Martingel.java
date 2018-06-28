/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seidl.casino;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Áron
 */
public class Martingel extends Player {

    int countLosedParty;
    private double prevMoney;
    private double initBet;
    private String chosenColour;
    private String name;

    public Martingel(String name, double initBet, String chosenColour, double money) {
        super(money);
        this.initBet = initBet;
        this.chosenColour = chosenColour;
        countLosedParty = 0;
        prevMoney = 0;
        this.name = name;
    }

    @Override
    public void nextBet() throws NoEnoughMoneyException {
        Scanner scanner = new Scanner(System.in);
        if (prevMoney >= money) {
            if (money == 0) {
                throw new NoEnoughMoneyException();
            } else if (money < Math.pow(2, countLosedParty) * initBet) {
                System.out.println("Mivel nincs már annyi pénzed amennyit fel szeretnél tenni, két lehetőséged maradt:");
                System.out.println("1: Felteszed minden pénzed, amid van 2: Kiszállsz a játékból");
                int agony = scanner.nextInt();
                switch (agony) {
                    case 1:
                        RedBlackBet r1 = new RedBlackBet(chosenColour, money);
                        betList.add(r1);
                        money = 0;
                        break;
                    case 2:
                        throw new NoEnoughMoneyException();
                    default:
                        System.out.println("Érvénytelen választ adtál meg, így ki vagy zárva a játékból.");
                        throw new NoEnoughMoneyException();
                }
            } else {
                RedBlackBet r1 = new RedBlackBet(chosenColour, Math.pow(2, countLosedParty) * initBet);
                money -= Math.pow(2, countLosedParty) * initBet;
                betList.add(r1);
            }
        } else {
            countLosedParty = 0;
            RedBlackBet r1 = new RedBlackBet(chosenColour, Math.pow(2, countLosedParty) * initBet);
            money -= Math.pow(2, countLosedParty) * initBet;
            betList.add(r1);
            prevMoney = money;
        }
    }

    @Override
    public List<Bet> getBetList() {
        return betList;
    }

    @Override
    public void pay(int winnerNumber) {
        double wonAmount = 0;
        for (Bet bet : betList) {
            wonAmount += bet.wonAmount(winnerNumber);
        }
        if (wonAmount == 0) {
            countLosedParty++;
        } else {
            countLosedParty = 0;
        }
        prevMoney = money;
        money = money + wonAmount;
        System.out.println(name + " üzeni: A kör végén " + money + " Forintom lett összesen!");
    }

}
