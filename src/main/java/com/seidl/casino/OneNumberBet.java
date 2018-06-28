/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seidl.casino;

/**
 *
 * @author Áron
 */
public class OneNumberBet extends Bet {

    int tip;

    public OneNumberBet(int tip, double amount) {
        super(amount);
        this.tip = tip;
    }

    @Override
    public double wonAmount(int winnerNumber) {
        try {
            if (tip > 36 || tip < 0) {
                throw new InvalidNumberException();
            }
            if (winnerNumber == tip) {
                return amount * 35;
            } else {
                return 0;
            }
        } catch (InvalidNumberException ex) {
            System.out.println("Mivel érvénytelen számot adtál meg, ebből a körből kimaradsz!");
            return amount;
        }
    }

}
