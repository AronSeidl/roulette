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
public class EvenOddBet extends Bet {

    String parity;

    public EvenOddBet(String parity, double amount) {
        super(amount);
        this.parity = parity;
    }

    @Override
    public double wonAmount(int winnerNumber) {
        try {
            if (!("even".equals(parity) || "odd".equals(parity))) {
                throw new InvalidParityException();
            }
            if (parity.equals("even")) {
                if (winnerNumber % 2 == 0 && winnerNumber != 0) {
                    return amount * 2;
                } else {
                    return 0;
                }
            } else {
                if (winnerNumber % 2 == 1) {
                    return amount * 2;
                } else {
                    return 0;
                }
            }
        } catch (InvalidParityException ex) {
            System.out.println("Érvénytelen párosságot adtál meg, úgyhogy ebből a körből kimaradsz!");
            return amount;
        }
    }

}
