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
public abstract class Bet {

    protected double amount;

    public Bet(double amount) {
        this.amount = amount;
    }

    public abstract double wonAmount(int winnerNumber);
}
