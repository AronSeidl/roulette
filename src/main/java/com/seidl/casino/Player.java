package com.seidl.casino;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Áron
 */
public abstract class Player {

    protected List<Bet> betList = new ArrayList<>();
    protected double money;

    public Player(double money) {
        this.money = money;
    }

    public abstract void nextBet() throws NoEnoughMoneyException;

    public abstract List<Bet> getBetList();

    public void pay(int winnerNumber) {
        double wonAmount = 0;
        for (Bet bet : betList) {
            wonAmount += bet.wonAmount(winnerNumber);
        }
        money = money + wonAmount;
        System.out.println("A kör végén " + money + " Forintom lett összesen!");
    }

    public double getMoney() {

        return money;

    }

}
